package GUI;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchData extends JFrame {

    private static final String API_URL = "https://dronesim.facets-labs.com/api/dronedynamics/?format=json";
    private static final String TOKEN = "Token 1586b43740b3c8b3686b31e2dc1cf1b4273b838f";

    private JTextArea resultTextArea;

    public FetchData() {
        super("API GUI");

        // Create components
        resultTextArea = new JTextArea(10, 30);
        JButton fetchDataButton = new JButton("Fetch Data");

        // Add action listener to the button
        fetchDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchDataFromApi();
            }
        });

        // Set up layout
        JPanel panel = new JPanel();
        panel.add(fetchDataButton);
        panel.add(new JScrollPane(resultTextArea));

        // Set up the frame
        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void fetchDataFromApi() {
        try {
            // Make HTTP request to the API
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Set the Authorization header with the token value
            connection.setRequestProperty("Authorization", TOKEN);

            // Read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Update the JTextArea with the API response
            resultTextArea.setText(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
            resultTextArea.setText("Error fetching data from API.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FetchData();
            }
        });
    }
}