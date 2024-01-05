package presentation;

import dataAccess.ClientDAO;
import dataAccess.OrderDAO;
import dataAccess.ProductDAO;
import model.Client;
import model.Orders;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.List;

public class Controller {
    private View view;
    private ClientView cv;
    private OrdersView ov;
    private ProductView pv;
    private ClientDAO clientDAO;
    private ProductDAO productDAO;
    private OrderDAO ordersDAO;
    public Controller(View view)
    {
        this.view = view;
        this.view.getClientButton().addActionListener(new ClientListener());
        this.view.getOrderButton().addActionListener(new OrderListener());
        this.view.getProductButton().addActionListener(new ProductListener());
        this.clientDAO = new ClientDAO();
        productDAO = new ProductDAO();
        ordersDAO = new OrderDAO();
    }

    /**
     * Secvența de cod definește o clasă OrderListener care implementează interfața ActionListener.
     * Această clasă este responsabilă pentru tratarea evenimentului de acțiune, în acest caz,
     * atunci când se produce o acțiune asociată unui obiect grafic.
     *
     * Metoda actionPerformed(ActionEvent e) este suprascrisă pentru a specifica acțiunile
     * care trebuie executate atunci când evenimentul de acțiune are loc.
     *
     * În interiorul metodei, se realizează următoarele acțiuni:
     *
     * 1. Se creează o instanță a clasei OrdersView și se atribuie referința la variabila ov.
     * 2. Se setează vizibilitatea obiectului ov, astfel încât fereastra OrdersView să fie afișată pe ecran.
     * 3. Se adaugă ascultători de acțiune pentru butoanele getViewButton() și getCreateOrderButton()
     * ale obiectului ov. Ascultătorii sunt instanțe ale claselor ShowOrder și AddOrder, care sunt
     * responsabile pentru tratarea evenimentelor specifice acestor butoane.
     * În concluzie, clasa OrderListener acționează ca un ascultător de acțiune pentru un
     * eveniment specific și inițiază acțiuni corespunzătoare în interfața utilizatorului.
     */

    class OrderListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            ov = new OrdersView();
            ov.setVisible(true);

            ov.getViewButton().addActionListener(new ShowOrder());
            ov.getCreateOrderButton().addActionListener(new AddOrder());
        }
    }

    /**
     * ca si la OrderListener
     */
    class ProductListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            pv=new ProductView();
            pv.setVisible(true);

            pv.getAddProductButton().addActionListener(new AddProduct());
            pv.getDeleteProductButton().addActionListener(new DeleteProduct());
            pv.getEditProductButton().addActionListener(new EditProduct());
            pv.getViewProductButton().addActionListener(new ShowProduct());
        }
    }

    /**
     * ca si la OrderListener
     */
    class ClientListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            cv=new ClientView();
            cv.setVisible(true);

            cv.getAddClientButton().addActionListener(new AddClient());
            cv.getDeleteClientButton().addActionListener(new DeleteClient());
            cv.getEditClientButton().addActionListener(new EditClient());
            cv.getViewClientButton().addActionListener(new ShowClient());
        }
    }

    /**
     * metoda folosita pentru afisarea tabelei
     * Secvența de cod definește o clasă ShowClient care implementează interfața ActionListener.
     * Această clasă este responsabilă pentru tratarea evenimentului de acțiune, în acest caz,
     * atunci când se produce o acțiune asociată unui obiect grafic.
     *
     * Metoda actionPerformed(ActionEvent e) este suprascrisă pentru a specifica acțiunile care
     * trebuie executate atunci când evenimentul de acțiune are loc.
     *
     * În interiorul metodei, se realizează următoarele acțiuni:
     *
     * Se apelează metoda scrieInTable() și se furnizează ca argumente tabela cv.getTable()
     * și rezultatul apelului metodei findAll() a obiectului clientDAO. Aceasta presupune afișarea
     * datelor clientului în tabela specificată.
     * În concluzie, clasa ShowClient acționează ca un ascultător de acțiune pentru un
     * eveniment specific și inițiază acțiuni corespunzătoare pentru afișarea datelor clientului într-o tabelă.
     */
    class ShowClient implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            scrieInTable(cv.getTable(), clientDAO.findAll());

        }
    }

    /**
     * metoda folosita pentru actualizare clinetilor din baza de date
     */
    class EditClient implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = cv.getIdTextField();
            String name = cv.getNameTextField();
            int age = cv.getAgeTextField();
            String email =cv.getEmailTextField();

            clientDAO.update(new Client(id, name, age, email));
        }
    }

    /**
     * metoda de sterfere a clientului din baza de date
     * sterge clientul din baza de date in functie de id ul pe care il dam
     */

    class DeleteClient implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            int id = cv.getIdTextField();
            clientDAO.delete(new Client(id,"",0, ""));

        }
    }

    /**
     * metoda de adaugare a clientuluyi in baza de date
     * adauga clinetul cu informatiile pe care le dam despre el
     * id ul se incrementeaza singur
     */
    class AddClient implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = cv.getNameTextField();
            int age = cv.getAgeTextField();
            String email =cv.getEmailTextField();

            clientDAO.insert(new Client(10, name, age, email));///id-ul e irelevant
        }
    }


    /**
     * Funcția scrieInTable primește ca parametri o instanță a clasei JTable și o listă de obiecte (List<?>).
     *
     * În cadrul funcției, se realizează următoarele acțiuni:
     *
     * 1. Se verifică dacă lista de obiecte este nulă sau goală. În caz afirmativ,
     * se afișează un mesaj și se încheie executarea funcției.
     * 2. Se obține modelul implicit al tabelei (DefaultTableModel) prin apelul metodei getModel() pe obiectul table.
     * 3. Se șterg datele existente din tabel prin setarea numărului de rânduri și coloane
     * la zero utilizând metoda setRowCount(0) și setColumnCount(0) a modelului.
     * 4. Se obține primul obiect din listă pentru a extrage numele proprietăților.
     * 5. Se obține clasa obiectului pentru a accesa câmpurile acestuia (Class<?> objectClass = firstObject.getClass()).
     * 6. Se obțin câmpurile obiectului prin apelul metodei getDeclaredFields().
     * 7. Se generează header-ul tabelului adăugând numele fiecărui câmp în modelul tabelului
     * utilizând metoda addColumn(columnName).
     * 8. Se generează datele tabelului parcugând fiecare obiect din lista și extrăgând
     * valorile pentru fiecare câmp. Se creează un vector de obiecte rowData pentru fiecare
     * rând al tabelului, iar valorile câmpurilor obiectului sunt atribuite în acest vector.
     * Apoi, vectorul rowData este adăugat în modelul tabelului utilizând metoda addRow(rowData).
     * Astfel, funcția scrieInTable este responsabilă de generarea și afișarea datelor obiectelor într-un tabel.
     */
    void scrieInTable(JTable table, List<?> objects)
    {
        if (objects == null || objects.isEmpty()) {
            System.out.println("No objects to generate table.");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Clear existing data from the table
        model.setRowCount(0);
        model.setColumnCount(0);

        // Get the first object from the list to extract the property names
        Object firstObject = objects.get(0);
        Class<?> objectClass = firstObject.getClass();
        Field[] fields = objectClass.getDeclaredFields();

        // Generate the table header data
        for (Field field : fields) {
            String columnName = field.getName();
            model.addColumn(columnName);
        }

        // Generate the table data
        for (Object object : objects) {
            Object[] rowData = new Object[fields.length];
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                try {
                    Object value = field.get(object);
                    rowData[i] = value;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            model.addRow(rowData);
        }
    }

    /**
     * la Produs este ca si la client
     */
    private class AddProduct implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int weight = pv.getWeightTextField();
            int quantity = pv.getQuantityTextField();
            String name = pv.getNameTextField();

            productDAO.insert(new Product(1,weight,quantity,name));
        }
    }

    private class DeleteProduct implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = pv.getIdTextField();

            productDAO.delete(new Product(id,0,0,""));
        }
    }

    private class EditProduct implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            int id = pv.getIdTextField();
            int weight = pv.getWeightTextField();
            int quantity = pv.getQuantityTextField();
            String name = pv.getNameTextField();

            productDAO.update(new Product(id,weight,quantity,name));

        }
    }

    private class ShowProduct implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            scrieInTable(pv.getTable(), productDAO.findAll());
        }
    }


    /**
     * metoda de plasare a comenzii. Intai verifica daca exisat clientul in baza de date.
     * Dupa care verifica daca exista produsul in depozit si daca sunt destule produse pentru comanda
     * actualizeaza cantitatea ramasa in depozit dupa realizarea comenzii
     * afiseaza mesaje daca conditiile nus unt indeplinite
     */
    private class AddOrder implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int clientId = ov.getClientIdTextField();
            int productId = ov.getProductIdTextField();
            int quantity = ov.getQuantityTextField();

            if (clientDAO.findById(clientId) == null)
            {
                ////NU EXISTA ACEL CLIENT
                System.out.println("Nu exista client");
                return;
            }

            if(productDAO.findById(productId) != null)
            {
                //EXISTA PRODUSUL
                if(productDAO.findById(productId).getQuantity() >= quantity)
                {
                    ///AM DESTULE
                    ordersDAO.insert(new Orders(0,clientId,productId,quantity));
                    Product produs = productDAO.findById(productId);
                    produs.setQuantity(produs.getQuantity() - quantity);
                    productDAO.update(produs);
                }
                else
                    System.out.println("Nu exista suficient produs in stoc");
            }
            else
                System.out.println("Nu exista produsul");

//            ordersDAO.insert(new Orders(0,clientId,productId,quantity));
        }
    }

    /**
     * ca la client
     */
    private class ShowOrder implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            scrieInTable(ov.getTable(), ordersDAO.findAll());
        }
    }
}
