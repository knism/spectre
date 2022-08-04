import org.knism.spectre.Spectre
import org.knism.spectre.app.App
import org.knism.spectre.app.objects.GameObject
import org.knism.spectre.app.scene.Scene
import org.knism.spectre.core.units.Offset
import org.knism.spectre.core.units.Size
import org.knism.spectre.rendering.camera.OpenGLCamera
import org.knism.spectre.rendering.renderables.OpenGLRenderable
import org.knism.spectre.rendering.renderer.OpenGLRenderer
import org.knism.spectre.window.Window
import org.knism.spectre.window.viewport.OpenGLViewport

fun main() {
    Spectre.di.provide(OpenGLRenderer())

    val maApp = object : App() {
        override fun init() {

            val button = GameObject().apply { name = "Button" }
            button.addComponent(OpenGLRenderable::class)

            val mainMenu = Scene().apply { name = "Main menu" }
            mainMenu.setCamera(OpenGLCamera())
            mainMenu.addObject(button)

            addScene(mainMenu)
            activateScene(mainMenu.uid)
        }
    }

    Window(
        size = Size(width = 640, height = 480), title = "Test", targetFPS = 60, viewport = OpenGLViewport(
            size = Size(width = 640, height = 480),
            offset = Offset(x = 0, y = 0)
        )
    ).start(maApp)
}