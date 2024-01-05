package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * clasa ProductView aranjeaza in pagina butoanele textfildul si tabelul
 * exista 4 obtiuni adaugare, stergere, vizualizare, si actualizare
 * contine gettere si settere folosite ulterior in Controller
 */

public class ProductView extends JFrame{

    private JTextField weightTextField;
    private JTextField quantityTextField;
    private JPanel contentPane;
    private JButton addProduct;
    private JTextField idTextField;
    private JTable table;
    private JTextField nameTextField;
    private JButton editProductButton;
    private JButton viewProductButton;
    private JButton deleteProductButton;

    public ProductView()
    {
        setBounds(100, 100, 1099, 679);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("Products");
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 29));
        titleLabel.setBounds(330, 36, 335, 45);
        contentPane.add(titleLabel);

        addProduct = new JButton("Add");
        addProduct.setFont(new Font("Tahoma", Font.PLAIN, 33));
        addProduct.setBounds(10, 141, 154, 58);
        contentPane.add(addProduct);

        deleteProductButton = new JButton("Delete");
        deleteProductButton.setFont(new Font("Tahoma", Font.PLAIN, 33));
        deleteProductButton.setBounds(10, 198, 154, 58);
        contentPane.add(deleteProductButton);

        viewProductButton = new JButton("View");
        viewProductButton.setFont(new Font("Tahoma", Font.PLAIN, 33));
        viewProductButton.setBounds(10, 250, 154, 63);
        contentPane.add(viewProductButton);

        editProductButton = new JButton("Edit");
        editProductButton.setFont(new Font("Tahoma", Font.PLAIN, 33));
        editProductButton.setBounds(10, 305, 154, 58);
        contentPane.add(editProductButton);

        nameTextField = new JTextField();
        nameTextField.setBounds(270, 134, 189, 24);
        contentPane.add(nameTextField);
        nameTextField.setColumns(10);

        JLabel nameLAbel = new JLabel("Name:");
        nameLAbel.setFont(new Font("Tahoma", Font.ITALIC, 18));
        nameLAbel.setBounds(180, 130, 81, 24);
        contentPane.add(nameLAbel);

        idTextField = new JTextField();
        idTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        idTextField.setColumns(10);
        idTextField.setBounds(270, 164, 189, 24);
        contentPane.add(idTextField);

        weightTextField = new JTextField();
        weightTextField.setColumns(10);
        weightTextField.setBounds(270, 225, 189, 24);
        contentPane.add(weightTextField);

        quantityTextField = new JTextField();
        quantityTextField.setColumns(10);
        quantityTextField.setBounds(270, 280, 189, 24);
        contentPane.add(quantityTextField);

        JLabel idLabel = new JLabel("Id:");
        idLabel.setFont(new Font("Tahoma", Font.ITALIC, 18));
        idLabel.setBounds(180, 164, 81, 24);
        contentPane.add(idLabel);

        JLabel weightLabel = new JLabel("Weight(g):");
        weightLabel.setFont(new Font("Tahoma", Font.ITALIC, 18));
        weightLabel.setBounds(180, 198, 120, 24);
        contentPane.add(weightLabel);

        JLabel quantityLabel = new JLabel("Quantity available:");
        quantityLabel.setFont(new Font("Tahoma", Font.ITALIC, 18));
        quantityLabel.setBounds(180, 250, 154, 36);
        contentPane.add(quantityLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(484, 78, 518, 529);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
    }

    public JButton getAddProductButton() {
        return addProduct;
    }

    public JButton getEditProductButton() {
        return editProductButton;
    }

    public JButton getViewProductButton() {
        return viewProductButton;
    }

    public JButton getDeleteProductButton() {
        return deleteProductButton;
    }

    public int getWeightTextField() {
        return Integer.parseInt(weightTextField.getText());
    }

    public int getQuantityTextField() {
        return Integer.parseInt(quantityTextField.getText());
    }

    public int getIdTextField() {
        return Integer.parseInt(idTextField.getText());
    }

    public JTable getTable() {
        return table;
    }

    public String getNameTextField() {
        return nameTextField.getText();
    }


}
