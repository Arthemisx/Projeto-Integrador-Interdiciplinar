import java.awt.*; // Importa classes para interface gráfica e manipulação de imagens
import java.awt.event.*; // Importa classes para eventos de interface gráfica
import java.awt.image.BufferedImage; // Para manipulação de imagens em buffer
import java.io.File; // Para manipulação de arquivos
import javax.imageio.ImageIO; // Para ler imagens de arquivos
import javax.swing.*; // Importa componentes gráficos Swing

// Classe principal da aplicação, herda de JFrame (janela)
public class ProjetoPI extends JFrame {

    private JButton iniciarButton; // Botão de iniciar
    public static Cursor customCursor; // Cursor personalizado
    private boolean iniciarButtonVisible = true; // Controle de visibilidade do botão

    // Construtor da janela principal
    public ProjetoPI() {
        setTitle("Bem-vindo!"); // Define o título da janela
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Fecha o app ao fechar a janela
        setSize(1800, 735); // Define o tamanho da janela
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setResizable(false); // Impede redimensionamento da janela

        setCursor(customCursor); // Define o cursor personalizado

        // Caminho da imagem de fundo
        String imagePath = "imagens/imagenspi/imagem de entrada.jpg";
        File imageFile = new File(imagePath); // Cria objeto de arquivo
        ImageIcon icon = imageFile.exists() ? new ImageIcon(imagePath) : null; // Carrega imagem se existir
        Image backgroundImage = (icon != null) ? icon.getImage() : null; // Obtém a imagem

        // Painel de fundo com a imagem
        BackgroundPanel panel = new BackgroundPanel(backgroundImage);
        panel.setLayout(null); // Layout absoluto (permite usar setBounds)

        // Criação do botão Iniciar
        iniciarButton = new JButton("Iniciar");
        try {
            // Tenta carregar fonte personalizada
            Font nexaFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/NexaRustSlab-Black.otf")).deriveFont(18f);
            iniciarButton.setFont(nexaFont);
        } catch (Exception e) {
            // Se não conseguir, usa fonte padrão
            System.err.println("Erro ao carregar a fonte: " + e.getMessage());
            iniciarButton.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        }
        iniciarButton.setBackground(new Color(0x5aa0dc)); // Cor de fundo do botão
        iniciarButton.setForeground(Color.WHITE); // Cor do texto do botão
        iniciarButton.addActionListener(e -> abrirTelaLogin()); // Ação ao clicar: abre tela de login
        // Torna o botão invisível mas funcional
        iniciarButton.setOpaque(false); // Não pinta o fundo
        iniciarButton.setContentAreaFilled(false); // Não preenche área de conteúdo
        iniciarButton.setBorderPainted(false); // Não desenha borda
        iniciarButton.setText(""); // Remove texto
        iniciarButton.setEnabled(true); // Continua funcional
        iniciarButton.setBounds(400, 445, 570, 70); // Define posição e tamanho do botão
        iniciarButton.setVisible(true); // Mantém o componente no painel

        panel.add(iniciarButton); // Adiciona o botão ao painel
        add(panel); // Adiciona o painel à janela
    }

    // Abre a tela de login
    private void abrirTelaLogin() {
        dispose(); // Fecha a janela atual
        TelaLogin login = new TelaLogin(); // Cria tela de login
        login.setVisible(true); // Exibe tela de login
    }

    // Método principal (main)
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> { // Garante execução na thread da interface gráfica
            try {
                // Tenta carregar cursor personalizado
                String cursorPath = "c:/Users/HP/Downloads/icon_mão-removebg-preview.png";
                Image cursorImage = ImageIO.read(new File(cursorPath));
                customCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                        cursorImage, new Point(0, 0), "CursorPersonalizado");
            } catch (Exception e) {
                // Se não conseguir, usa cursor padrão
                System.err.println("Erro ao definir cursor personalizado: " + e.getMessage());
                customCursor = Cursor.getDefaultCursor();
            }

            ProjetoPI projectPi = new ProjetoPI(); // Cria janela principal
            projectPi.setVisible(true); // Exibe janela principal
        });
    }
}

// Classe da tela intermediária, exibida após login
class TelaIntermediaria extends JFrame {
    private JButton btnOpcao1, btnOpcao2; // Dois botões de opções

    public TelaIntermediaria() {
        setTitle("Menu Intermediário"); // Título da janela
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Fecha ao sair
        setSize(1800, 730); // Tamanho da janela
        setLocationRelativeTo(null); // Centraliza
        setResizable(false); // Não redimensionável
        setCursor(ProjetoPI.customCursor); // Usa cursor personalizado

        String imagePath = "imagens/imagenspi/tela de opções.jpg"; // Caminho da imagem de fundo
        File imageFile = new File(imagePath);
        ImageIcon backgroundIcon = imageFile.exists() ? new ImageIcon(imagePath) : null;
        Image backgroundImage = (backgroundIcon != null) ? backgroundIcon.getImage() : null;

        BackgroundPanel panel = new BackgroundPanel(backgroundImage); // Painel de fundo
        panel.setLayout(null); // Layout absoluto

        // Botão Opção 1 (modifique os valores de setBounds para alterar posição/tamanho)
        btnOpcao1 = new JButton("");
        btnOpcao1.setBounds(377, 341, 618, 78); // x, y, width, height
        btnOpcao1.setOpaque(false);
        btnOpcao1.setContentAreaFilled(false);
        btnOpcao1.setBorderPainted(false);
        btnOpcao1.setBackground(new Color(0, 0, 0, 100)); // Semi-transparente
        btnOpcao1.setForeground(Color.WHITE);
        btnOpcao1.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        btnOpcao1.addActionListener(e -> abrirPainelPrincipal());
        panel.add(btnOpcao1);

        // Botão Opção 2 (modifique os valores de setBounds para alterar posição/tamanho)
        btnOpcao2 = new JButton("");
        btnOpcao2.setBounds(377, 440, 618, 78); // x, y, width, height
        btnOpcao2.setOpaque(false);
        btnOpcao2.setContentAreaFilled(false);
        btnOpcao2.setBorderPainted(false);
        btnOpcao2.setBackground(new Color(0, 0, 0, 100)); // Semi-transparente
        btnOpcao2.setForeground(Color.WHITE);
        btnOpcao2.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        btnOpcao2.addActionListener(e -> abrirPainelPrincipal());
        panel.add(btnOpcao2);

        add(panel); // Adiciona painel à janela
    }

    private void abrirPainelPrincipal() {
        dispose(); // Fecha janela atual
        TelaPrincipal telaPrincipal = new TelaPrincipal(); // Cria tela principal
        telaPrincipal.setVisible(true); // Exibe tela principal
    }
}

// Classe da tela de login
class TelaLogin extends JFrame {
    private JTextField nomeUsuarioField; // Campo nome do usuário
    private JTextField nomeAdministradorField; // Campo email admin
    private JPasswordField passwordField; // Campo senha
    private JButton entrarButton; // Botão Entrar

    public TelaLogin() {
        initUI(); // Inicializa interface
    }

    // Inicializa interface
    private void initUI() {
        setTitle("Metrô day - Login"); // Título
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Fecha ao sair
        setSize(1800, 730); // Tamanho
        setLocationRelativeTo(null); // Centraliza
        setResizable(false); // Não redimensionável
        setCursor(ProjetoPI.customCursor); // Cursor personalizado

        // Configurações visuais do JOptionPane
        UIManager.put("OptionPane.minimumSize", new Dimension(300, 100));
        UIManager.put("OptionPane.border", BorderFactory.createEmptyBorder(5, 5, 5, 5));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("Panel.background", Color.DARK_GRAY);

        String imagePath = "imagens/imagenspi/imagem cadastro.jpg"; // Imagem de fundo
        File imageFile = new File(imagePath);
        ImageIcon backgroundIcon = imageFile.exists() ? new ImageIcon(imagePath) : null;
        Image backgroundImage = (backgroundIcon != null) ? backgroundIcon.getImage() : null;

        BackgroundPanel mainPanel = new BackgroundPanel(backgroundImage); // Painel de fundo
        mainPanel.setLayout(null); // Layout absoluto para posicionamento livre
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margem

        // Campo nome do usuário (mude os valores de setBounds para alterar posição/tamanho)
        JLabel nomeUsuarioLabel = new JLabel("Nome do Usuário:");
        nomeUsuarioLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        nomeUsuarioLabel.setBounds(400, 270, 200, 30); // x, y, width, height
        mainPanel.add(nomeUsuarioLabel);

        // Campo email admin (mude os valores de setBounds para alterar posição/tamanho)
        JLabel nomeLabel = new JLabel("Email de Administrador:");
        nomeLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        nomeLabel.setBounds(400, 350, 220, 30); // x, y, width, height (ajustado para ficar acima do campo)
        mainPanel.add(nomeLabel);

        // Campo senha (mude os valores de setBounds para alterar posição/tamanho)
        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        senhaLabel.setBounds(395, 420, 590, 65); // x, y, width, height
        mainPanel.add(senhaLabel);

        // Adicione os campos de texto após os botões para ficarem "na frente"
        nomeUsuarioField = new JTextField(20);
        nomeUsuarioField.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        nomeUsuarioField.setBounds(390, 250, 590, 65); // x, y, width, height
        nomeUsuarioField.setOpaque(false);
        nomeUsuarioField.setBackground(new Color(0,0,0,0));
        nomeUsuarioField.setBorder(BorderFactory.createEmptyBorder());
        nomeUsuarioField.setForeground(Color.BLACK); // Texto visível
        nomeUsuarioField.setCaretColor(Color.BLACK);
        nomeUsuarioField.setVisible(true);
        nomeUsuarioField.setEnabled(true);
        nomeUsuarioField.setEditable(true);
        mainPanel.add(nomeUsuarioField);

        nomeAdministradorField = new JTextField(20);
        nomeAdministradorField.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
        nomeAdministradorField.setBounds(395, 340, 590, 65); // x, y, width, height
        nomeAdministradorField.setOpaque(false);
        nomeAdministradorField.setBackground(new Color(0,0,0,0));
        nomeAdministradorField.setBorder(BorderFactory.createEmptyBorder());
        nomeAdministradorField.setForeground(Color.BLACK); // Texto visível
        nomeAdministradorField.setCaretColor(Color.BLACK);
        nomeAdministradorField.setVisible(true);
        nomeAdministradorField.setEnabled(true);
        nomeAdministradorField.setEditable(true);
        mainPanel.add(nomeAdministradorField);

        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        passwordField.setBounds(395, 420, 590, 65); // x, y, width, height
        passwordField.setOpaque(false);
        passwordField.setBackground(new Color(0,0,0,0));
        passwordField.setBorder(BorderFactory.createEmptyBorder());
        passwordField.setForeground(Color.BLACK); // Texto visível
        passwordField.setCaretColor(Color.BLACK);
        passwordField.setVisible(true);
        passwordField.setEnabled(true);
        passwordField.setEditable(true);
        mainPanel.add(passwordField);

        // Agora adicione o label 'Email de Administrador' e o botão Entrar por último para ficarem "na frente"
        mainPanel.add(nomeLabel);

        entrarButton = new JButton("Entrar");
        entrarButton.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        entrarButton.setBackground(Color.BLUE);
        entrarButton.setForeground(Color.WHITE);
        entrarButton.addActionListener(this::entrarAction); // Ação de login
        // Altere os valores abaixo para mudar posição e tamanho do botão Entrar
        entrarButton.setBounds(480, 520, 400, 80); // x, y, width, height
        // Torna o botão invisível mas funcional
        entrarButton.setOpaque(false);
        entrarButton.setContentAreaFilled(false);
        entrarButton.setBorderPainted(false);
        entrarButton.setText("");
        entrarButton.setEnabled(true);
        entrarButton.setVisible(true);
        mainPanel.add(entrarButton);

        add(mainPanel); // Adiciona painel à janela
    }

    // Ação do botão Entrar
    private void entrarAction(ActionEvent evt) {
        String nomeUsuario = nomeUsuarioField.getText();
        String emailAdmin = nomeAdministradorField.getText();
        String senha = new String(passwordField.getPassword());
        if ("admin".equals(senha)) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido!\nBem-vindo, " + nomeUsuario + "!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            TelaIntermediaria telaIntermediaria = new TelaIntermediaria();
            telaIntermediaria.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Senha incorreta!", "Erro de Login", JOptionPane.ERROR_MESSAGE);
        }
    }
}

// Classe da tela principal do painel do metrô
class TelaPrincipal extends JFrame {
    // Classe interna para configuração das áreas clicáveis
    private static class AreaConfig {
        Rectangle bounds; // Retângulo da área
        String imagePath; // Caminho da imagem de zoom
        String title; // Título da área
        AreaConfig(int x, int y, int width, int height, String imagePath, String title) {
            this.bounds = new Rectangle(x, y, width, height);
            this.imagePath = imagePath;
            this.title = title;
        }
    }

    // Áreas clicáveis do painel
    private final AreaConfig[] areasConfig = {
        new AreaConfig(90, 300, 250, 150, 
                      "imagens/imagenspi/painel_zoom_1.jpg.jpg", 
                      "Painel - Área 1"),
        new AreaConfig(350, 250, 100, 100, 
                      "imagens/imagenspi/05 - Módulo de Comunicação - tela de início.jpg", 
                      "Painel- Área 2"),
        new AreaConfig(500, 250, 330, 150, 
                      "imagens/imagenspi/02 - ADU e sinaleiras.jpg", 
                      "Painel- Area 3"),
        new AreaConfig(950, 250, 370, 150, 
                      "imagens/imagenspi/04 - VDU.jpg", 
                      "Painel- Area 4")
    };

    private Image backgroundImage; // Imagem de fundo

    public TelaPrincipal() {
        setTitle("Painel do Metrô"); // Título
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Fecha ao sair
        setSize(1800, 730); // Tamanho
        setLocationRelativeTo(null); // Centraliza
        setResizable(false); // Não redimensionável
        setCursor(ProjetoPI.customCursor); // Cursor personalizado

        String imagePath = "imagens/imagenspi/01 - Painel.jpg"; // Imagem de fundo
        File imageFile = new File(imagePath);
        ImageIcon backgroundIcon = imageFile.exists() ? new ImageIcon(imagePath) : null;
        backgroundImage = (backgroundIcon != null) ? backgroundIcon.getImage() : null;

        // Painel de fundo desenha as áreas
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

        // MouseListener para detectar cliques nas áreas
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

        // MouseMotionListener para mudar o cursor ao passar sobre áreas
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
                mainPanel.setCursor(sobreArea ? 
                    new Cursor(Cursor.HAND_CURSOR) : 
                    ProjetoPI.customCursor);
            }
        });

        add(mainPanel); // Adiciona painel à janela
    }

    // Abre o zoom da área clicada
    private void abrirZoom(AreaConfig areaConfig) {
        JDialog zoomDialog = new JDialog(this, areaConfig.title, true); // Diálogo modal
        zoomDialog.setCursor(ProjetoPI.customCursor); // Cursor personalizado
        ImageIcon zoomIcon = loadCustomImage(areaConfig.imagePath, areaConfig.title); // Carrega imagem
        JLabel lblImagem = new JLabel(zoomIcon) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(zoomIcon.getIconWidth(), zoomIcon.getIconHeight());
            }
        };
        JScrollPane scrollPane = new JScrollPane(lblImagem);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int maxWidth = (int)(screenSize.width * 0.8);
        int maxHeight = (int)(screenSize.height * 0.8);
        int prefWidth = Math.min(zoomIcon.getIconWidth() + 20, maxWidth);
        int prefHeight = Math.min(zoomIcon.getIconHeight() + 60, maxHeight);
        scrollPane.setPreferredSize(new Dimension(prefWidth, prefHeight));
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        btnVoltar.addActionListener(e -> zoomDialog.dispose());
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnVoltar);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        zoomDialog.add(panel);
        zoomDialog.pack();
        zoomDialog.setLocationRelativeTo(this);
        zoomDialog.setVisible(true);
    }

    // Carrega imagem customizada ou gera imagem de erro
    private ImageIcon loadCustomImage(String imagePath, String areaTitle) {
        try {
            File file = new File(imagePath);
            if (file.exists()) {
                BufferedImage img = ImageIO.read(file);
                return new ImageIcon(img);
            } else {
                System.err.println("Imagem não encontrada: " + imagePath);
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem: " + e.getMessage());
        }
        // Gera imagem de erro se não encontrar
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

// Painel de fundo que desenha a imagem
class BackgroundPanel extends JPanel {
    private final Image image; // Imagem de fundo
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
