package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * clasa View contine meniul de start, in cazul nostru titlul si 3 butoane
 * pentru a accesa baza de date entru client, produs si comanda
 */
public class View extends JFrame {
    private final JButton clientButton;
    private final JPanel contentPane;
    private final JButton productButton;
    private final JButton orderButton;

    public View(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 911, 502);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("Database Menu");
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 29));
        titleLabel.setBounds(350, 38, 335, 45);///pozitia pe ecran OR coordonate
        contentPane.add(titleLabel);

        clientButton = new JButton("Clients\r\n");
        clientButton.setFont(new Font("Tahoma", Font.PLAIN, 33));
        clientButton.setBounds(88, 197, 244, 88);
        contentPane.add(clientButton);

        orderButton = new JButton("Orders\r\n");
        orderButton.setFont(new Font("Tahoma", Font.PLAIN, 34));
        orderButton.setBounds(331, 197, 244, 88);
        contentPane.add(orderButton);

        productButton = new JButton("Product");
        productButton.setFont(new Font("Tahoma", Font.PLAIN, 34));
        productButton.setBounds(574, 197, 244, 88);
        contentPane.add(productButton);


        this.setVisible(true);

    }


    public JButton getClientButton() {
        return clientButton;
    }

    public JButton getProductButton() {
        return productButton;
    }

    public JButton getOrderButton() {
        return orderButton;
    }
}
