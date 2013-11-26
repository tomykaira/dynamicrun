package io.github.tomykaira.dynamicrun;

public class Main {
    private static final String testCode =
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
        String code = compiler.normalizeCode(testCode);
        // "test/test" has no meaning, the last part should equal to the class name (because public)
        compiler.compileSource(new DynamicJavaCodeObject("test/test/RandomCharacter", code));
        Character character = compiler.compiledCharacterClass("RandomCharacter").newInstance();
        for (int i = 0; i < 10; i ++) {
            character.walk();
        }
    }
}
