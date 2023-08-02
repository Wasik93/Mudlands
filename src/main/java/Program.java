import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

class Program {
    public static void main(String[] args) {
        new Lwjgl3Application(new MudlandsGame(), createConfiguration());
    }

    private static Lwjgl3ApplicationConfiguration createConfiguration() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setIdleFPS(Config.FPS);
        configuration.useVsync(Config.VSYNC_ENABLED);
        configuration.setWindowedMode(Config.NATIVE_WIDTH, Config.NATIVE_HEIGHT);
        configuration.setTitle(Config.NAME);
        return configuration;
    }
}
