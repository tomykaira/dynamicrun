package io.github.tomykaira.dynamicrun;

public class Main {
    private static final String testCode =
            "package io.github.tomykaira.dynamicrun;\n" +
                    "public class RandomCharacter extends Character {\n" +
                    "\n" +
                    "    @Override\n" +
                    "    public void walk() {\n" +
                    "        int type = (int)(Math.random() * 4);\n" +
                    "        switch (type) {\n" +
                    "            case 0:\n" +
                    "                up();\n" +
                    "                break;\n" +
                    "            case 1:\n" +
                    "                down();\n" +
                    "                break;\n" +
                    "            case 2:\n" +
                    "                left();\n" +
                    "                break;\n" +
                    "            case 3:\n" +
                    "                right();\n" +
                    "                break;\n" +
                    "        }\n" +
                    "    }\n" +
                    "}";

    public static void main(String[] args) throws Exception {
        DynamicCompiler compiler = new DynamicCompiler();
        DynamicJavaCodeObject codeObject = new DynamicJavaCodeObject(testCode);
        if (!compiler.compileSource(codeObject)) {
            throw new RuntimeException("Compilation failed");
        }
        Character character = compiler.compiledCharacterClass(codeObject).newInstance();
        for (int i = 0; i < 10; i ++) {
            character.walk();
        }
    }
}
