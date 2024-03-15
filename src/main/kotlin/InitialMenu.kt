import javax.swing.*
import java.awt.*
import java.awt.event.*

object InitialMenu : AbstractMenu() {
    override val options = arrayOf("Comenzar", "Cargar Juego", "Salir")
    override var selectedIndex = 0

    init {
        val titleText = """
██████  ██ ███████  ██████   ██████       ██████  █████  ███    ██  ██████       █████  ██████  ██    ██ ███████ ███    ██ ████████ ██    ██ ██████  ███████
██   ██ ██ ██      ██       ██    ██     ██      ██   ██ ████   ██ ██    ██     ██   ██ ██   ██ ██    ██ ██      ████   ██    ██    ██    ██ ██   ██ ██
██   ██ ██ █████   ██   ███ ██    ██     ██      ███████ ██ ██  ██ ██    ██     ███████ ██   ██ ██    ██ █████   ██ ██  ██    ██    ██    ██ ██████  █████
██   ██ ██ ██      ██    ██ ██    ██     ██      ██   ██ ██  ██ ██ ██    ██     ██   ██ ██   ██  ██  ██  ██      ██  ██ ██    ██    ██    ██ ██   ██ ██
██████  ██ ███████  ██████   ██████       ██████ ██   ██ ██   ████  ██████      ██   ██ ██████    ████   ███████ ██   ████    ██     ██████  ██   ██ ███████
"""

        val titleLabel = JLabel("<html><pre>$titleText</pre></html>")
        titleLabel.foreground = Color.WHITE
        titleLabel.font = Font("Monospaced", Font.PLAIN, 16)
        titleLabel.horizontalAlignment = SwingConstants.CENTER
        titleLabel.isOpaque = true
        titleLabel.background = Color.BLACK

        val titlePanel = JPanel()
        titlePanel.layout = BoxLayout(titlePanel, BoxLayout.PAGE_AXIS)
        titlePanel.background = Color.BLACK
        titlePanel.add(Box.Filler(Dimension(0, 100), Dimension(0, 200), Dimension(0, 300)))
        titlePanel.add(titleLabel)

        frame.add(titlePanel, BorderLayout.NORTH)

        setupMenu()
    }


    override fun handleMenuOption() {
        when (selectedIndex) {
            0 -> {
                frame.dispose()
                val gameWindow = GameWindow()


                val thread = Thread {
                    gameWindow.switchToNameInput()
                }

                thread.start()

                val thread2 = Thread {
                    while (gameWindow.playerName == null || gameWindow.selectedRace == null || gameWindow.selectedClass == null) {
                        Thread.sleep(5000)
                    }

                    val player = gameWindow.player

                    frame.dispose()
                    GameFrame(player!!)
                }

                thread2.start()
            }

            1 -> cargarJuego()
            2 -> salir()
        }
    }

    private fun comenzarJuego() {
        println("Comenzando juego...")
    }

    private fun cargarJuego() {
        println("Cargando juego guardado...")
    }

    private fun salir() {
        println("Saliendo de la aplicación...")
        System.exit(0)
    }
}