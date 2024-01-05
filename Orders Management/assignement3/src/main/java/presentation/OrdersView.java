package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * clasa OrderView arangeaza in pagina butoanele textfildul si tabelul
 * exista 2 obtiuni plasare comanda, si vizualizare
 * contine gettere si settere folosite ulterior in Controller
 */

public class OrdersView extends JFrame{
    private JPanel contentPane;
    private JTable table;
    private JTextField clientIdTextField;
    private JTextField productIdTextField;
    private JButton viewButton;
    private JTextField quantityTextField;
    private JButton createOrderButton;

    public OrdersView()
    {
        setBounds(100, 100, 1099, 679);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("Orders");
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));
        titleLabel.setBounds(77, 24, 206, 45);
        contentPane.add(titleLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(484, 78, 518, 529);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));

        clientIdTextField = new JTextField();
        clientIdTextField.setBounds(230, 95, 96, 19);
        contentPane.add(clientIdTextField);
        clientIdTextField.setColumns(10);

        viewButton = new JButton("View orders");
        viewButton.setFont(new Font("Tahoma", Font.PLAIN, 21));
        viewButton.setBounds(180, 372, 189, 27);
        contentPane.add(viewButton);

        productIdTextField = new JTextField();
        productIdTextField.setColumns(10);
        productIdTextField.setBounds(230, 138, 96, 19);
        contentPane.add(productIdTextField);

        JLabel clientIdLabel = new JLabel("Client Id for order");
        clientIdLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        clientIdLabel.setBounds(10, 95, 177, 19);
        contentPane.add(clientIdLabel);

        JLabel productIdLabel = new JLabel("Product Id for order");
        productIdLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        productIdLabel.setBounds(10, 138, 185, 19);
        contentPane.add(productIdLabel);

        JLabel quantityLabel = new JLabel("Desired quantity:\r\n");
        quantityLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        quantityLabel.setBounds(10, 184, 177, 19);
        contentPane.add(quantityLabel);

        quantityTextField = new JTextField();
        quantityTextField.setColumns(10);
        quantityTextField.setBounds(230, 184, 96, 19);
        contentPane.add(quantityTextField);

        createOrderButton = new JButton("Create Order\r\n");
        createOrderButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        createOrderButton.setBounds(180, 287, 187, 33);
        contentPane.add(createOrderButton);
    }

    public JTable getTable() {
        return table;
    }

    public int getClientIdTextField() {
        return Integer.parseInt(clientIdTextField.getText());
    }

    public int getProductIdTextField() {
        return Integer.parseInt(productIdTextField.getText());
    }

    public JButton getViewButton() {
        return viewButton;
    }

    public int getQuantityTextField() {
        return Integer.parseInt(quantityTextField.getText());
    }

    public JButton getCreateOrderButton() {
        return createOrderButton;
    }
}
