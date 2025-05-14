import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

// Utilitários comuns para todas as telas
class UIUtils {
    // Carrega uma imagem de arquivo
    public static Image loadImage(String path) {
        File imageFile = new File(path);
        if (imageFile.exists()) {
            return new ImageIcon(path).getImage();
        }
        return null;
    }

    // Cria um botão invisível (apenas área clicável)
    public static JButton createInvisibleButton(Rectangle bounds, ActionListener listener) {
        JButton button = new JButton();
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setBounds(bounds);
        if (listener != null) button.addActionListener(listener);
        return button;
    }

    // Cria um botão estilizado para opções
    public static JButton createOptionButton(String text, Rectangle bounds, ActionListener listener) {
        JButton button = new JButton(text);
        button.setBounds(bounds);
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(true);
        button.setBackground(new Color(0, 0, 0, 100));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        if (listener != null) button.addActionListener(listener);
        return button;
    }
}

// Painel de fundo que desenha a imagem
class BackgroundPanel extends JPanel {
    private final Image image;
    public BackgroundPanel(Image backgroundImage) {
        this.image = backgroundImage;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

// Tela de entrada (Bem-vindo)
public class ProjetoPI extends JFrame {
    public static Cursor customCursor;

    public ProjetoPI() {
        setTitle("Bem-vindo!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1800, 735);
        setLocationRelativeTo(null);
        setResizable(false);
        setCursor(customCursor);
        initComponents();
    }

    private void initComponents() {
        Image backgroundImage = UIUtils.loadImage("imagens/imagenspi/imagem de entrada.jpg");
        BackgroundPanel panel = new BackgroundPanel(backgroundImage);
        panel.setLayout(null);
        // Botão invisível para iniciar
        JButton iniciarButton = UIUtils.createInvisibleButton(
            new Rectangle(400, 445, 570, 70),
            e -> abrirTelaLogin()
        );
        panel.add(iniciarButton);
        add(panel);
    }

    private void abrirTelaLogin() {
        dispose();
        new TelaLogin().setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                String cursorPath = "imagens/imagenspi/icon_mão-removebg-preview.png";
                Image cursorImage = ImageIO.read(new File(cursorPath));
                customCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                        cursorImage, new Point(0, 0), "CursorPersonalizado");
            } catch (Exception e) {
                customCursor = Cursor.getDefaultCursor();
            }
            new ProjetoPI().setVisible(true);
        });
    }
}

// Tela de cadastro/login
class TelaLogin extends JFrame {
    private JTextField nomeUsuarioField;
    private JTextField nomeAdministradorField;
    private JPasswordField passwordField;

    public TelaLogin() {
        setTitle("Metrô day - Cadastro");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1800, 730);
        setLocationRelativeTo(null);
        setResizable(false);
        setCursor(ProjetoPI.customCursor);
        initComponents();
    }

    private void initComponents() {
        Image backgroundImage = UIUtils.loadImage("imagens/imagenspi/imagem cadastro.jpg");
        BackgroundPanel mainPanel = new BackgroundPanel(backgroundImage);
        mainPanel.setLayout(null);

        JLabel nomeUsuarioLabel = new JLabel("Nome do Usuário:");
        nomeUsuarioLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        nomeUsuarioLabel.setBounds(400, 270, 200, 30);
        mainPanel.add(nomeUsuarioLabel);

        nomeUsuarioField = new JTextField(20);
        nomeUsuarioField.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        nomeUsuarioField.setBounds(400, 260, 590, 40);
        nomeUsuarioField.setOpaque(false);
        nomeUsuarioField.setBorder(BorderFactory.createEmptyBorder());
        nomeUsuarioField.setForeground(Color.BLACK);
        nomeUsuarioField.setCaretColor(Color.BLACK);
        mainPanel.add(nomeUsuarioField);
        // Esconde o label quando o campo não está vazio
        nomeUsuarioField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { update(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { update(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { update(); }
            private void update() {
                nomeUsuarioLabel.setVisible(nomeUsuarioField.getText().isEmpty());
            }
        });

        JLabel nomeLabel = new JLabel("Email de Administrador:");
        nomeLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        nomeLabel.setBounds(400, 360, 220, 30);
        mainPanel.add(nomeLabel);

        nomeAdministradorField = new JTextField(20);
        nomeAdministradorField.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
        nomeAdministradorField.setBounds(400, 350, 590, 40);
        nomeAdministradorField.setOpaque(false);
        nomeAdministradorField.setBorder(BorderFactory.createEmptyBorder());
        nomeAdministradorField.setForeground(Color.BLACK);
        nomeAdministradorField.setCaretColor(Color.BLACK);
        mainPanel.add(nomeAdministradorField);
        // Esconde o label quando o campo não está vazio
        nomeAdministradorField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { update(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { update(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { update(); }
            private void update() {
                nomeLabel.setVisible(nomeAdministradorField.getText().isEmpty());
            }
        });

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        senhaLabel.setBounds(400, 445, 100, 30);
        mainPanel.add(senhaLabel);

        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        passwordField.setBounds(400, 440, 590, 40);
        passwordField.setOpaque(false);
        passwordField.setBorder(BorderFactory.createEmptyBorder());
        passwordField.setForeground(Color.BLACK);
        passwordField.setCaretColor(Color.BLACK);
        mainPanel.add(passwordField);
        // Esconde o label quando o campo não está vazio
        passwordField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { update(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { update(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { update(); }
            private void update() {
                senhaLabel.setVisible(passwordField.getPassword().length == 0);
            }
        });

        // Botão invisível para entrar
        JButton entrarButton = UIUtils.createInvisibleButton(
            new Rectangle(480, 580, 400, 50),
            this::entrarAction
        );
        mainPanel.add(entrarButton);
        add(mainPanel);
    }

    private void entrarAction(ActionEvent evt) {
        String nomeUsuario = nomeUsuarioField.getText();
        String senha = new String(passwordField.getPassword());
        if ("admin".equals(senha)) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido!\nBem-vindo, " + nomeUsuario + "!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            new TelaIntermediaria().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Senha incorreta!", "Erro de Login", JOptionPane.ERROR_MESSAGE);
        }
    }
}

// Tela intermediária (menu de opções)
class TelaIntermediaria extends JFrame {
    public TelaIntermediaria() {
        setTitle("Menu Intermediário");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1800, 730);
        setLocationRelativeTo(null);
        setResizable(false);
        setCursor(ProjetoPI.customCursor);
        initComponents();
    }

    private void initComponents() {
        Image backgroundImage = UIUtils.loadImage("imagens/imagenspi/tela de opções.jpg");
        BackgroundPanel panel = new BackgroundPanel(backgroundImage);
        panel.setLayout(null);

        JButton btnOpcao1 = UIUtils.createInvisibleButton(
            new Rectangle(390, 341, 595, 80),
            e -> abrirPainelPrincipal()
        );
        panel.add(btnOpcao1);

        JButton btnOpcao2 = UIUtils.createInvisibleButton(
            new Rectangle(390, 442 , 595, 80),
            e -> abrirPainelPrincipal()
        );
        panel.add(btnOpcao2);

        add(panel);
    }

    private void abrirPainelPrincipal() {
        dispose();
        new TelaPrincipal().setVisible(true);
    }
}

// Tela principal do painel do metrô
class TelaPrincipal extends JFrame {
    // Configuração das áreas clicáveis
    private static class AreaConfig {
        Rectangle bounds;
        String imagePath;
        String title;
        AreaConfig(int x, int y, int width, int height, String imagePath, String title) {
            this.bounds = new Rectangle(x, y, width, height);
            this.imagePath = imagePath;
            this.title = title;
        }
    }

    private final AreaConfig[] areasConfig = {
        new AreaConfig(90, 300, 250, 150, "imagens/imagenspi/painel_zoom_1.jpg.jpg", "Painel - Área 1"),
        new AreaConfig(350, 250, 100, 100, "imagens/imagenspi/05 - Módulo de Comunicação - tela de início.jpg", "Painel- Área 2"),
        new AreaConfig(500, 250, 330, 150, "imagens/imagenspi/02 - ADU e sinaleiras.jpg", "Painel- Area 3"),
        new AreaConfig(950, 250, 370, 150, "imagens/imagenspi/04 - VDU.jpg", "Painel- Area 4")
    };

    public TelaPrincipal() {
        setTitle("Painel do Metrô");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1800, 730);
        setLocationRelativeTo(null);
        setResizable(false);
        setCursor(ProjetoPI.customCursor);
        initComponents();
    }

    private void initComponents() {
        Image backgroundImage = UIUtils.loadImage("imagens/imagenspi/01 - Painel.jpg");
        BackgroundPanel mainPanel = new BackgroundPanel(backgroundImage) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                for (AreaConfig area : areasConfig) {
                    g.drawRect(area.bounds.x, area.bounds.y, area.bounds.width, area.bounds.height);
                }
            }
        };
        mainPanel.setLayout(null);
        // Mouse listeners para áreas clicáveis
        mainPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point pontoClique = e.getPoint();
                for (AreaConfig area : areasConfig) {
                    if (area.bounds.contains(pontoClique)) {
                        abrirZoom(area);
                        break;
                    }
                }
            }
        });
        mainPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                boolean sobreArea = false;
                for (AreaConfig area : areasConfig) {
                    if (area.bounds.contains(e.getPoint())) {
                        sobreArea = true;
                        break;
                    }
                }
                mainPanel.setCursor(sobreArea ? new Cursor(Cursor.HAND_CURSOR) : ProjetoPI.customCursor);
            }
        });
        add(mainPanel);
    }

    // Abre o zoom da área clicada
    private void abrirZoom(AreaConfig areaConfig) {
        JDialog zoomDialog = new JDialog(this, areaConfig.title, true);
        zoomDialog.setCursor(ProjetoPI.customCursor);
        ImageIcon zoomIcon = loadCustomImage(areaConfig.imagePath, areaConfig.title);
        int imgWidth = zoomIcon.getIconWidth();
        int imgHeight = zoomIcon.getIconHeight();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int maxWidth = (int) (screenSize.width * 0.95);
        int maxHeight = (int) (screenSize.height * 0.85);

        final ImageIcon iconToShow;
        if (imgWidth > maxWidth || imgHeight > maxHeight) {
            double scale = Math.min((double)maxWidth / imgWidth, (double)maxHeight / imgHeight);
            int newW = (int)(imgWidth * scale);
            int newH = (int)(imgHeight * scale);
            Image scaled = zoomIcon.getImage().getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
            iconToShow = new ImageIcon(scaled);
        } else {
            iconToShow = zoomIcon;
        }

        JLabel lblImagem = new JLabel(iconToShow) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(iconToShow.getIconWidth(), iconToShow.getIconHeight());
            }
        };
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        btnVoltar.addActionListener(e -> zoomDialog.dispose());
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(lblImagem, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnVoltar);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        zoomDialog.add(panel);
        zoomDialog.pack();
        zoomDialog.setLocationRelativeTo(this);
        zoomDialog.setVisible(true);
    }

    // Carrega imagem customizada ou placeholder
    private ImageIcon loadCustomImage(String imagePath, String areaTitle) {
        try {
            File file = new File(imagePath);
            if (file.exists()) {
                BufferedImage img = ImageIO.read(file);
                return new ImageIcon(img);
            }
        } catch (Exception ignored) {}
        BufferedImage img = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, 800, 600);
        g.setColor(Color.BLUE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        String[] lines = areaTitle.split(" ");
        int y = 250;
        for (String line : lines) {
            g.drawString(line, 100, y);
            y += 40;
        }
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Imagem não encontrada em: " + imagePath, 100, y + 50);
        g.dispose();
        return new ImageIcon(img);
    }
}