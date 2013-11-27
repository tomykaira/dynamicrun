package io.github.tomykaira.dynamicrun;

import javax.tools.*;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class DynamicCompiler {
    private final Path destinationDirectory;

    public DynamicCompiler() throws IOException {
        this.destinationDirectory = Files.createTempDirectory("user-code");
    }

    /**
     * Compile given DynamicJavaCodeObject into destinationDirectory
     * @param codeObject the code input by a user
     * @return true if compilation succeeds
     */
    public boolean compileSource(DynamicJavaCodeObject codeObject) {
        DiagnosticCollector<JavaFileObject> diagnosticCollector = new DiagnosticCollector<>();
        List<String> options = Arrays.asList("-d", destinationDirectory.toString());
        List<? extends JavaFileObject> sources = Arrays.asList(codeObject);

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        JavaCompiler.CompilationTask task = compiler.getTask(null, null, diagnosticCollector, options, null, sources);

        if (!task.call()) {
            for (Diagnostic<? extends JavaFileObject> diagnostic : diagnosticCollector.getDiagnostics()) {
                // TODO: refine in order to be understandable
                System.err.printf("%s:%d\t%s", diagnostic.getCode(), diagnostic.getLineNumber(), diagnostic.getMessage(Locale.ENGLISH));
            }
            return false;
        }
        return true;
    }

    /**
     * Load compiled class.
     * The result must be cached.
     *
     * @param codeObject the compiled code object
     * @return the class found in the temporary directory
     * @throws ClassNotFoundException, ClassCastException
     */
    public Class<? extends Character> compiledCharacterClass(DynamicJavaCodeObject codeObject) throws ClassNotFoundException, IOException {
        URLClassLoader classLoader = new URLClassLoader(new URL[]{ destinationDirectory.toUri().toURL() });
        return classLoader.loadClass(codeObject.packageName + '.' + codeObject.className).asSubclass(Character.class);
    }
}
