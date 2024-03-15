import javax.swing.*
import java.awt.*
import java.awt.event.*

class ClassMenu(private val gameWindow: GameWindow) : JPanel() {
    val options: Array<String> = arrayOf("Spacial Knight", "Shooter", "Colonel")
    var selectedIndex = 0
    var selectedClass: String = ""
    val menuLabels = mutableListOf<JLabel>()

    init {
        background = Color.BLACK
        layout = GridBagLayout()

        setupMenu()

        addKeyListener(object : KeyAdapter() {
            override fun keyPressed(e: KeyEvent) {
                when (e.keyCode) {
                    KeyEvent.VK_UP -> moveSelectionUp()
                    KeyEvent.VK_DOWN -> moveSelectionDown()
                    KeyEvent.VK_ENTER -> handleMenuOption()
                }
            }
        })
        isFocusable = true
    }


    fun setupMenu() {
        val menuPanel = JPanel(GridBagLayout())
        menuPanel.background = Color.BLACK
        menuPanel.border = BorderFactory.createLineBorder(Color.WHITE, 2)

        val gbc = GridBagConstraints() // Define gbc here
        gbc.gridx = 0
        gbc.gridy = 0
        gbc.fill = GridBagConstraints.HORIZONTAL

        options.forEachIndexed { index, option ->
            val label = JLabel(option)
            label.font = Font("Monospaced", Font.PLAIN, 16)
            label.horizontalAlignment = SwingConstants.CENTER
            label.border = if (index == 1) {
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
            } else {
                BorderFactory.createEmptyBorder(20, 40, 20, 40)
            }

            if (index == 0) {
                label.foreground = Color.BLACK
                label.background = Color.WHITE
                label.isOpaque = true
            } else {
                label.foreground = Color.WHITE
            }

            menuPanel.add(label, gbc)
            menuLabels.add(label)
            gbc.gridy++
        }

        add(menuPanel)
    }

    fun moveSelectionUp() {
        if (selectedIndex > 0) {
            menuLabels[selectedIndex].foreground = Color.WHITE
            menuLabels[selectedIndex].isOpaque = false
            selectedIndex--
            menuLabels[selectedIndex].foreground = Color.BLACK
            menuLabels[selectedIndex].background = Color.WHITE
            menuLabels[selectedIndex].isOpaque = true
        }
    }

    fun moveSelectionDown() {
        if (selectedIndex < options.size - 1) {
            menuLabels[selectedIndex].foreground = Color.WHITE
            menuLabels[selectedIndex].isOpaque = false
            selectedIndex++
            menuLabels[selectedIndex].foreground = Color.BLACK
            menuLabels[selectedIndex].background = Color.WHITE
            menuLabels[selectedIndex].isOpaque = true
        }
    }
    fun getTextPanel(): JPanel {
        val textLabel = JLabel("Choose your race")
        textLabel.font = Font(textLabel.font.name, Font.PLAIN, 20)
        textLabel.foreground = Color.WHITE
        textLabel.background = Color.BLACK

        // Add underline to the text
        val underline = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE)
        textLabel.border = BorderFactory.createCompoundBorder(textLabel.border, underline)

        // Center the JLabel
        textLabel.horizontalAlignment = SwingConstants.CENTER

        val textPanel = JPanel(GridBagLayout())
        textPanel.background = Color.BLACK
        val gbc = GridBagConstraints()
        gbc.gridx = 0
        gbc.gridy = 0
        gbc.insets = Insets(50, 0, 0, 0)
        textPanel.add(textLabel, gbc)

        return textPanel
    }

    fun handleMenuOption() {
        selectedClass = when (selectedIndex) {
            0 -> "Spacial Knight"
            1 -> "Shooter"
            2 -> "Colonel"
            else -> ""
        }
        gameWindow.selectedClass = selectedClass
        println(gameWindow.selectedClass)
        gameWindow.constructPlayer()
        gameWindow.switchToLoadingScreen()
    }

    fun classReturned(): String {
        return selectedClass
    }
}