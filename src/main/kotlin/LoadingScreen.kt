import javax.swing.*
import java.awt.*

class LoadingScreen : JPanel() {
    init {
        background = Color.BLACK
        layout = GridBagLayout()

        val loadingLabel = JLabel("Cargando")
        loadingLabel.font = Font("Monospaced", Font.PLAIN, 50)
        loadingLabel.foreground = Color.WHITE
        loadingLabel.horizontalAlignment = SwingConstants.CENTER

        add(loadingLabel)
    }
}