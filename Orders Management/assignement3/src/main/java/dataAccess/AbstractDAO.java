package dataAccess;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 * @Source http://www.java-blog.com/mapping-javaobjects-database-reflection-generics
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    /**
     * Această secvență este un constructor al clasei AbstractDAO.
     * Acest constructor este folosit pentru a inițializa variabila type a clasei cu tipul generic T
     * asociat clasei concrete care extinde clasa AbstractDAO.
     */

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    /**
     * Secvența de cod prezentată definește o metodă privată numită createSelectQuery,
     * care primește un parametru de tip String numit field și returnează o interogare SELECT sub formă de șir de caractere.
     * Explicație detaliată a codului:
     * 1. StringBuilder sb = new StringBuilder();: Se creează un obiect de tip StringBuilder,
     * care este utilizat pentru a construi șirul de caractere pentru interogare.
     * 2. sb.append("SELECT ");: Se adaugă în StringBuilder cuvântul cheie "SELECT",
     * indicând că interogarea va returna un set de rezultate.
     * 3. sb.append(" * ");: Se adaugă în StringBuilder asterisc (*) pentru a selecta toate coloanele din tabelul specificat.
     * 4. sb.append(" FROM ");: Se adaugă în StringBuilder cuvântul cheie "FROM",
     * indicând tabela din care se va face selecția.
     * 5. sb.append(type.getSimpleName());: Se adaugă în StringBuilder numele simplu al tipului generic T,
     * care a fost inițializat în constructorul clasei. Acesta este numele tabelei din care se va face selecția.
     * 6. sb.append(" WHERE " + field + " =?");: Se adaugă în StringBuilder o clauză WHERE care
     * specifică condiția de selecție. Parametrul field este numele coloanei pe baza căreia se va face filtrarea,
     * iar semnul întrebării (?) reprezintă un parametru predefinit care va fi înlocuit cu o valoare reală
     * atunci când se va executa interogarea.
     * 7. return sb.toString();: Se returnează șirul de caractere construit prin apelul
     * metodei toString() a obiectului StringBuilder.
     * În final, metoda createSelectQuery returnează șirul de caractere care reprezintă interogarea SELECT completă,
     * având în vedere tabela și condiția specificate.
     * @param field
     * @return
     */

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * Secvența de cod prezentată definește o metodă publică numită findAll,
     * care returnează o listă de obiecte de tip T. Această metodă efectuează o interogare
     * SELECT pentru a obține toate înregistrările din tabela specificată și le transformă
     * în obiecte utilizând metoda createObjects.
     * Explicație pe scurt a codului:
     * 1. Se inițializează variabilele connection, statement și resultSet cu valoarea null.
     * Acestea vor fi utilizate pentru gestionarea conexiunii cu baza de date și a rezultatelor interogării.
     * 2. Se construiește interogarea SELECT utilizând numele simplu al tipului generic T și
     * se atribuie rezultatul variabilei query.
     * 3. Se încearcă obținerea conexiunii la baza de date prin apelul metodei getConnection
     * din clasa ConnectionFactory și se atribuie rezultatul variabilei connection.
     * 4. Se pregătește instrucțiunea SQL utilizând interogarea query prin apelul metodei
     * prepareStatement a obiectului connection și se atribuie rezultatul variabilei statement.
     * 5. Se execută interogarea SQL utilizând metoda executeQuery a obiectului statement
     * și se atribuie rezultatul variabilei resultSet.
     * 6. Se apelează metoda createObjects pentru a transforma rezultatele din resultSet
     * în obiecte și se returnează lista rezultată.
     * 7. În cazul în care apare o excepție de tipul SQLException în blocul catch,
     * se înregistrează un mesaj de avertizare în jurnalul de evenimente.
     * 8. În final, se închid obiectele resultSet, statement și connection utilizând
     * metodele close din clasa ConnectionFactory.
     * Se returnează null în cazul în care apare o excepție sau nu se pot obține înregistrări din baza de date.
     * Această metodă permite obținerea tuturor înregistrărilor dintr-o tabelă specificată
     * și transformarea lor într-o listă de obiecte de tip T
     * @return
     */

    public List<T> findAll() {
        // TODO:
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + type.getSimpleName();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Secvența de cod prezentată definește o metodă publică numită findById, care primește
     * un parametru de tip int reprezentând ID-ul și returnează un obiect de tip T corespunzător
     * înregistrării cu ID-ul specificat.
     * Explicație pe scurt a codului:
     *
     * 1. Se inițializează variabilele connection, statement și resultSet cu valoarea null.
     * Acestea vor fi utilizate pentru gestionarea conexiunii cu baza de date și a rezultatelor interogării.
     *
     * 2. Se construiește interogarea SELECT utilizând metoda createSelectQuery, specificând că
     * se dorește să se găsească înregistrarea cu ID-ul specificat.
     *
     * 3. Se încearcă obținerea conexiunii la baza de date prin apelul metodei getConnection
     * din clasa ConnectionFactory și se atribuie rezultatul variabilei connection.
     *
     * 4. Se pregătește instrucțiunea SQL utilizând interogarea query prin apelul metodei
     * prepareStatement a obiectului connection și se atribuie rezultatul variabilei statement.
     *
     * 5. Se setează parametrul din instrucțiunea SQL pentru ID utilizând metoda setInt a obiectului statement.
     *
     * 6. Se execută interogarea SQL utilizând metoda executeQuery a obiectului statement
     * și se atribuie rezultatul variabilei resultSet.
     *
     * 7. Se apelează metoda createObjects pentru a transforma rezultatele din resultSet
     * în obiecte și se returnează primul obiect din lista rezultată.
     *
     * 8. În cazul în care apare o excepție de tipul SQLException în blocul catch,
     * se înregistrează un mesaj de avertizare în jurnalul de evenimente.
     *
     * 9. În final, se închid obiectele resultSet, statement și connection utilizând
     * metodele close din clasa ConnectionFactory.
     *
     * 10. Se returnează null în cazul în care apare o excepție sau nu se găsește o înregistrare cu ID-ul specificat.
     *
     * Această metodă permite găsirea unei înregistrări în funcție de ID și returnarea obiectului corespunzător.
     * @param id
     * @return
     */

    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Secvența de cod prezentată definește o metodă privată numită createObjects care
     * primește un obiect ResultSet și returnează o listă de obiecte de tip T.
     *
     * Explicație pe scurt a codului:
     *
     * 1. Se inițializează o listă goală list de tip ArrayList<T>. Aceasta va fi utilizată pentru a stoca obiectele create.
     *
     * 2. Se obțin constructorii declarați ai clasei type și se stochează într-un array ctors.
     *
     * 3. Se parcurge array-ul ctors pentru a găsi primul constructor fără parametri
     * (adică ctor.getGenericParameterTypes().length == 0) și se atribuie acesta la variabila ctor.
     *
     * 4. Se parcurge fiecare rând din rezultatul obținut prin apelul metodei next() a obiectului resultSet.
     *
     * 5. Se activează accesul la constructorul ctor prin apelul metodei setAccessible(true).
     *
     * 6. Se creează o nouă instanță a clasei type prin apelul metodei newInstance()
     * a obiectului ctor și se atribuie rezultatul variabilei instance.
     *
     * 7. Se parcurg toate câmpurile declarate ale clasei type utilizând for-each loop.
     *
     * 8. Pentru fiecare câmp, se obține numele câmpului și se stochează în variabila fieldName.
     *
     * 9. Se obține valoarea câmpului din resultSet utilizând metoda getObject(fieldName)
     * și se atribuie rezultatul variabilei value.
     *
     * 10. Se obține descriptorul proprietății pentru câmpul respectiv prin crearea unui obiect
     * PropertyDescriptor cu numele câmpului și clasa type.
     *
     * 11. Se obține metoda setter corespunzătoare câmpului prin apelul metodei getWriteMethod()
     * a obiectului propertyDescriptor și se atribuie rezultatul variabilei method.
     *
     * 12. Se apelează metoda invoke() a obiectului method, în care se specifică instanța
     * instance și valoarea value, pentru a seta valoarea câmpului în instanța obiectului.
     *
     * 13. Se adaugă instanța obiectului în listă.
     *
     * 14. În cazul în care apar excepții de diferite tipuri, acestea sunt afișate prin apelul metodei printStackTrace().
     *
     * 15. Se returnează lista de obiecte creată.
     *
     * Această metodă realizează transformarea rezultatelor din resultSet în obiecte de tip T,
     * folosind metodele setter ale claselor și valorile corespunzătoare din resultSet.
     * @param resultSet
     * @return
     */

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Secvența de cod prezentată definește o metodă publică numită delete care primește un obiect
     * de tip T și returnează obiectul șters sau null în caz de eroare.
     *
     * Explicație pe scurt a codului:
     *
     * 1. Se inițializează o variabilă connection de tip Connection și o variabilă statement de
     * tip PreparedStatement cu valoarea null.
     *
     * 2. Se obține numele tabelului din clasa type și se stochează în variabila tableName.
     *
     * 3. În blocul try, se construiește șirul de caractere query pentru ștergerea
     * înregistrărilor din tabel, utilizând tableName și condiția WHERE id = ?.
     *
     * 4. Se obține o conexiune la baza de date prin apelul metodei getConnection()
     * a clasei ConnectionFactory și se atribuie rezultatul variabilei connection.
     *
     * 5. Se pregătește declarația statement utilizând conexiunea și șirul de caractere
     * query prin apelul metodei prepareStatement() a obiectului connection.
     *
     * 6. Se obține câmpul id al clasei type prin apelul metodei getDeclaredField("id")
     * și se atribuie rezultatul variabilei idField.
     *
     * 7. Se activează accesul la câmpul id prin apelul metodei setAccessible(true) a obiectului idField.
     *
     * 8. Se obține valoarea câmpului id din obiectul t prin apelul metodei get()
     * a obiectului idField și se atribuie rezultatul variabilei id.
     *
     * 9. Se setează valoarea parametrului ? din declarația statement cu valoarea id utilizând metoda setObject().
     *
     * 10. Se execută declarația de actualizare (DELETE) prin apelul metodei executeUpdate() a obiectului statement.
     *
     * 11. Se returnează obiectul t care a fost șters.
     *
     * 12. În cazul în care apar excepții de tip SQLException, IllegalAccessException
     * sau NoSuchFieldException, acestea sunt tratate și afișate în consolă.
     *
     * 13. În blocul finally, se închid declarația statement și conexiunea
     * connection utilizând metodele close() ale clasei ConnectionFactory.
     *
     * 14. Dacă se ajunge la acest punct, înseamnă că a apărut o eroare și se returnează null.
     *
     * Această metodă realizează ștergerea unui obiect t din tabelul corespunzător
     * clasei type bazându-se pe valoarea câmpului id.
     * @param t
     * @return
     */

    public T delete(T t)
    {
        //DELETE FROM table_name
        //WHERE condition;
        Connection connection = null;
        PreparedStatement statement = null;
        String tableName = type.getSimpleName();

        try {
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("DELETE FROM ").append(tableName).append(" WHERE id = ?");
            String query = queryBuilder.toString();

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            Field idField = type.getDeclaredField("id");
            idField.setAccessible(true);
            Object id = idField.get(t);
            statement.setObject(1, id);
            // Execute the update statement
            statement.executeUpdate();

            return t;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }

    /**
     * ecvența de cod prezentată definește o metodă publică numită insert care primește un obiect
     * de tip T și returnează obiectul inserat sau null în caz de eroare.
     *
     * Explicație pe scurt a codului:
     *
     * 1. Se inițializează o variabilă connection de tip Connection și o variabilă
     * statement de tip PreparedStatement cu valoarea null.
     *
     * 2. Se obține numele tabelului din clasa type și se stochează în variabila tableName.
     *
     * 3. În blocul try, se obține lista de câmpuri (fields) pentru entitatea type utilizând metoda getDeclaredFields().
     *
     * 4. Se construiește șirul de caractere query pentru inserarea înregistrărilor
     * în tabel, utilizând tableName și lista de câmpuri.
     *
     * 5. Se obține o conexiune la baza de date prin apelul metodei getConnection()
     * a clasei ConnectionFactory și se atribuie rezultatul variabilei connection.
     *
     * 6. Se pregătește declarația statement utilizând conexiunea și șirul de
     * caractere query prin apelul metodei prepareStatement() a obiectului connection.
     *
     * 7. Se parcurge lista de câmpuri (fields) și se adaugă numele câmpurilor în
     * declarația statement și se setează valorile parametrilor (?).
     *
     * 8. Se obține valoarea câmpului curent din obiectul t prin apelul
     * metodei get() a obiectului fields[i] și se atribuie rezultatul variabilei value.
     *
     * 9. Se setează valoarea parametrului ? din declarația statement cu valoarea value utilizând metoda setObject().
     *
     * 10. Se execută declarația de actualizare (INSERT) prin apelul metodei executeUpdate() a obiectului statement.
     *
     * 11. Se returnează obiectul t care a fost inserat.
     *
     * 12. În cazul în care apare o excepție de tip SQLException sau IllegalAccessException,
     * aceasta este tratată și afișată în consolă.
     *
     * 13. În blocul finally, se închid declarația statement și conexiunea connection
     * utilizând metodele close() ale clasei ConnectionFactory.
     *
     * 14. Dacă se ajunge la acest punct, înseamnă că a apărut o eroare și se returnează null.
     *
     * Această metodă realizează inserarea unui obiect t în tabelul corespunzător clasei type,
     * setând valorile câmpurilor obiectului în parametrii declarației SQL.
     * @param t
     * @return
     */
    public T insert(T t) {
        // TODO:
        ///INSERT INTO employees (id, name, age, salary)
        //VALUES (1, 'John Doe', 30, 50000);


        Connection connection = null;
        PreparedStatement statement = null;
        String tableName = type.getSimpleName();

        try {
            // Get the list of fields for the entity
            Field[] fields = type.getDeclaredFields();

            // Build the UPDATE query
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("INSERT INTO ").append(tableName).append(" (");

            for (int i = 1; i < fields.length; i++) {
                String fieldName = fields[i].getName();
                queryBuilder.append(fieldName);

                if (i < fields.length - 1) {
                    queryBuilder.append(", ");
                }
                else {
                    queryBuilder.append(") VALUES (");

                }

            }

            for (int i = 1; i < fields.length; i++) {

                if (i < fields.length - 1) {
                    queryBuilder.append("?, ");
                }
                else
                    queryBuilder.append("?) ");
            }

            String query = queryBuilder.toString();
//            System.out.println(query);

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            // Set the field values in the prepared statement
            int parameterIndex = 1;
            for (int i = 1; i < fields.length; i++) {///inlocuieste ? cu valoarea dorita
                fields[i].setAccessible(true);
                Object value = fields[i].get(t);
                statement.setObject(parameterIndex, value);
                parameterIndex++;
            }


            // Execute the update statement
            statement.executeUpdate();

            return t;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null; // Return null if the insert was not successful
    }

    /**
     * Secvența de cod prezentată definește o metodă numită update care
     * primește un obiect t și returnează obiectul actualizat. Această metodă este
     * responsabilă de actualizarea înregistrărilor dintr-o tabelă a bazei de date.
     *
     * Mai întâi, se construiește interogarea de actualizare (UPDATE) prin concatenarea
     * numelui tabelului și a câmpurilor pe care se dorește a fi actualizate.
     * Apoi, se creează conexiunea la baza de date și se pregătește declarația (PreparedStatement) cu interogarea construită.
     *
     * Următorul pas este atribuirea valorilor câmpurilor din obiectul t în declarația pregătită.
     * Aceste valori sunt preluate folosind reflexia și sunt setate în declarație cu ajutorul metodei setObject().
     *
     * Se atribuie și valoarea câmpului "id" în declarație, care este necesară pentru
     * a identifica înregistrarea specifică care trebuie actualizată.
     *
     * După aceea, declarația este executată cu ajutorul metodei executeUpdate(),
     * care întoarce numărul de înregistrări actualizate. Dacă există cel puțin o
     * înregistrare actualizată, se returnează obiectul t actualizat, altfel se returnează null.
     *
     * În caz de erori, se gestionează excepțiile, se înregistrează mesajele de avertizare
     * și se închid resursele conexiunii și declarației.
     * @param t
     * @return
     */

    public T update(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String tableName = type.getSimpleName();

        try {
            // Get the list of fields for the entity
            Field[] fields = type.getDeclaredFields();

            // Build the UPDATE query
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("UPDATE ").append(tableName).append(" SET ");
            ///UPDATE TABLE_NAME
            ////SET name = ?, age = ?, email = ?,
            ///WHERE id = ?

            for (int i = 1; i < fields.length; i++) {
                String fieldName = fields[i].getName();
                queryBuilder.append(fieldName).append(" = ?");

                if (i < fields.length - 1) {
                    queryBuilder.append(", ");
                }
            }

            queryBuilder.append(" WHERE id = ?");
            String query = queryBuilder.toString();

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            // Set the field values in the prepared statement
            int parameterIndex = 1;
            for (int i = 1; i < fields.length; i++) {///inlocuieste ? cu valoarea dorita
                fields[i].setAccessible(true);
                Object value = fields[i].get(t);
                statement.setObject(parameterIndex, value);
                parameterIndex++;
            }

            // Set the id value in the prepared statement
            Field idField = type.getDeclaredField("id");
            idField.setAccessible(true);
            Object idValue = idField.get(t);
            statement.setObject(parameterIndex, idValue);

            // Execute the update statement
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                return t; // Return the updated entity
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null; // Return null if the update was not successful
    }

}
