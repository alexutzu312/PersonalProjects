package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * clasa ClientView aranjeaza in pagina butoanele textfildul si tabelul.
 * exista 4 obtiuni adaugare, stergere, vizualizare, si actualizare
 * contine gettere si settere folosite ulterior in Controller
 */
public class ClientView extends JFrame{

    private JTextField idTextField;
    private JTextField ageTextField;
    private JTextField emailTextField;
    private JButton editClientButton;
    private JButton deleteClientButton;
    private JButton viewClientButton;
    private JTextField nameTextField;
    private JPanel contentPane;
    private JButton addClientButton;
    private JTable table;

    public ClientView()
    {
        setBounds(100, 100, 1099, 679);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("Client");
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 29));
        titleLabel.setBounds(323, 40, 335, 45);
        contentPane.add(titleLabel);

        addClientButton = new JButton("Add");
        addClientButton.setFont(new Font("Tahoma", Font.PLAIN, 33));
        addClientButton.setBounds(10, 141, 154, 58);
        contentPane.add(addClientButton);


        deleteClientButton = new JButton("Delete");
        deleteClientButton.setFont(new Font("Tahoma", Font.PLAIN, 33));
        deleteClientButton.setBounds(10, 198, 154, 58);
        contentPane.add(deleteClientButton);

        viewClientButton = new JButton("View");
        viewClientButton.setFont(new Font("Tahoma", Font.PLAIN, 33));
        viewClientButton.setBounds(10, 250, 154, 63);
        contentPane.add(viewClientButton);

        editClientButton = new JButton("Edit");
        editClientButton.setFont(new Font("Tahoma", Font.PLAIN, 33));
        editClientButton.setBounds(10, 305, 154, 58);
        contentPane.add(editClientButton);

        nameTextField = new JTextField();
        nameTextField.setBounds(285, 130, 189, 24);
        contentPane.add(nameTextField);
        nameTextField.setColumns(10);

        JLabel nameLAbel = new JLabel("Name:");
        nameLAbel.setFont(new Font("Tahoma", Font.ITALIC, 18));
        nameLAbel.setBounds(194, 130, 81, 24);
        contentPane.add(nameLAbel);

        idTextField = new JTextField();
        idTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        idTextField.setColumns(10);
        idTextField.setBounds(285, 175, 189, 24);
        contentPane.add(idTextField);

        ageTextField = new JTextField();
        ageTextField.setColumns(10);
        ageTextField.setBounds(285, 241, 189, 24);
        contentPane.add(ageTextField);

        emailTextField = new JTextField();
        emailTextField.setColumns(10);
        emailTextField.setBounds(285, 305, 189, 24);
        contentPane.add(emailTextField);

        JLabel idLabel = new JLabel("Id:");
        idLabel.setFont(new Font("Tahoma", Font.ITALIC, 18));
        idLabel.setBounds(194, 164, 81, 24);
        contentPane.add(idLabel);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setFont(new Font("Tahoma", Font.ITALIC, 18));
        ageLabel.setBounds(194, 241, 81, 24);
        contentPane.add(ageLabel);

        JLabel addressLabel = new JLabel("Email:");
        addressLabel.setFont(new Font("Tahoma", Font.ITALIC, 18));
        addressLabel.setBounds(194, 302, 81, 24);
        contentPane.add(addressLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(484, 78, 518, 529);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
    }

    public JButton getEditClientButton() {
        return editClientButton;
    }

    public JButton getDeleteClientButton() {
        return deleteClientButton;
    }

    public JButton getViewClientButton() {
        return viewClientButton;
    }

    public JButton getAddClientButton() {
        return addClientButton;
    }

    public JTable getTable() {
        return table;
    }

    public int getIdTextField() {
        return Integer.parseInt(idTextField.getText());
    }

    public int getAgeTextField() {
        return Integer.parseInt(ageTextField.getText());
    }

    public String getEmailTextField() {
        return emailTextField.getText();
    }

    public String getNameTextField() {
        return nameTextField.getText();
    }
}
