package io.github.tomykaira.dynamicrun;

import javax.tools.SimpleJavaFileObject;
import java.io.IOException;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DynamicJavaCodeObject extends SimpleJavaFileObject {

    private final String code;
    public final String packageName;
    public final String className;

    /**
     * Create DynamicJavaCodeObject from code and its context
     * @param code Java source code with package (optional) and public class
     */
    protected DynamicJavaCodeObject(String code) {
        super(URI.create("string:///" + fileNameFromCode(code, Kind.SOURCE.extension)), Kind.SOURCE);
        this.code = code;
        this.packageName = packageNameFromCode(code);
        this.className = classNameFromCode(code);
    }

    private static String fileNameFromCode(String code, String ext) {
        String path = packageNameFromCode(code).replace('.', '/');
        if (!path.isEmpty())
            path += '/';
        return path + classNameFromCode(code) + ext;
    }

    private static String packageNameFromCode(String code) {
        Matcher m = Pattern.compile("^package\\s+([.a-zA-Z0-9_]*)\\s*;$", Pattern.MULTILINE).matcher(code);
        if (m.find()) {
            return m.group(1);
        } else {
            return "";
        }
    }

    private static String classNameFromCode(String code) {
        Matcher m = Pattern.compile("^public\\s+class\\s+([a-zA-Z0-9_]*)", Pattern.MULTILINE).matcher(code);
        if (m.find()) {
            return m.group(1);
        } else {
            return "";
        }
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        return code;
    }
}
