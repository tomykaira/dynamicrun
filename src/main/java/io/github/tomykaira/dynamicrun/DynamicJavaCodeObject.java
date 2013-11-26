package io.github.tomykaira.dynamicrun;

import javax.tools.SimpleJavaFileObject;
import java.io.IOException;
import java.net.URI;

public class DynamicJavaCodeObject extends SimpleJavaFileObject {

    private final String code;

    /**
     * Create DynamicJavaCodeObject from code and its context
     * @param context slash separated context  e.g. "player1/chapter3/stage2"
     * @param code Java source code
     */
    protected DynamicJavaCodeObject(String context, String code) {
        super(URI.create("string:///" + context + Kind.SOURCE.extension), Kind.SOURCE);
        this.code = code;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        return code;
    }
}
