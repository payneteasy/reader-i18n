package com.payneteasy.reader.i18n;

import com.payneteasy.android.sdk.reader.CardErrorType;
import com.payneteasy.android.sdk.reader.CardReaderProblem;
import com.payneteasy.android.sdk.reader.CardReaderState;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.TreeSet;

public class PropertyFileGenerator {

    private final TreeSet<String> keys;
    private final StringBuilder codeText;

    public PropertyFileGenerator() {
        keys = new TreeSet<String>();
        codeText = new StringBuilder();
    }

    public static void main(String[] args) {
        PropertyFileGenerator generator = new PropertyFileGenerator();
        generator.generate();
        generator.print(new PrintWriter(System.out));
    }

    private void print(PrintWriter aWriter) {
        for (String key : keys) {
            aWriter.println(key + " =");
            aWriter.flush();
        }

        System.out.println(codeText);
    }

    private void generate() {
        // generate reader state
//        generateReaderState();
//        generateOnCardError();
        generateCardReaderProblem();

    }

    private void generateCardReaderProblem() {
        code("switch(aCardReaderProblem) '{'");
        for (CardReaderProblem state : CardReaderProblem.values()) {
            String subKey = state.name();
            String key = addKey("problem", subKey);
            code("    case {0}: return translator.get(\"{1}\");", subKey, key);
        }
        code("'}'");
    }

    private void generateReaderState() {
        code("switch(aEvent.getState()) '{'");
        for (CardReaderState state : CardReaderState.values()) {
            String subKey = state.name();
            if (state.getMessageClassname() == null) {
                String key = addKey("reader", subKey);
                code("    case {0}: return aTranslator.get(\"{1}\");", subKey, key);
            } else {
                addKeyWithInspection("reader", subKey, state.getMessageClassname());
            }
        }
        code("'}'");
    }

    private void generateOnCardError() {
        code("switch(aCardError.getType()) '{'");
        for (CardErrorType state : CardErrorType.values()) {
            String subKey = state.name();
            String key = addKey("error", subKey);
            code("    case {0}: return translator.get(\"{1}\");", subKey, key);
        }
        code("'}'");
    }

    private void code(String aFormat, String ... args) {
        MessageFormat format = new MessageFormat(aFormat);
        codeText.append(format.format(args)).append('\n');
    }

    private void addKeyWithInspection(String aPrefix, String aSubKey, String aClassname)  {
        try {
            Class messageClass = Thread.currentThread().getContextClassLoader().loadClass(aClassname);
            EnumFieldName enumClass    = findOnlyOneEnum(messageClass);
            if(enumClass != null) {
                code("    case {0}: '{'", aSubKey);
                code("        {0} message = ({0}) aEvent.getMessage();", messageClass.getSimpleName());
                code("        switch(message.{0}) '{'", enumClass.fieldName);
                for (Object cons : enumClass.enumClass.getEnumConstants()) {
                    String key = addKey(aPrefix, aSubKey, cons.toString());
                    code("            case {0}: return aTranslator.get(\"{1}\");", cons.toString(), key);
                }
                code("        '}'");
                code("    '}'");
            } else {
                // custom
                code("    case {0}: return get_{0}(aTranslator, ({1})aEvent.getMessage());", aSubKey, messageClass.getSimpleName());
            }
        } catch (Exception e) {
            throw new IllegalStateException("Can't inspect class " + aClassname, e);
        }

    }

    private EnumFieldName findOnlyOneEnum(Class aMessageClass) {
        EnumFieldName enumClass = null;
        for (Field field : aMessageClass.getFields()) {
            Class type = field.getType();
            if(type.isEnum()) {
                if(enumClass == null) {
                    enumClass = new EnumFieldName(type, field.getName());
                } else {
                    // there are more than 1 enums
                    return null;
                }
            }
        }
        return enumClass;
    }

    private String addKey(String ... tokens) {
        StringBuilder sb = new StringBuilder();
        for (String token : tokens) {
            if(sb.length() != 0) {
                sb.append('.');
            }
            sb.append(normalizeToken(token));
        }
        keys.add(sb.toString());
        return sb.toString();

    }

    private String normalizeToken(String aToken) {
        if(!shouldBeNormalized(aToken)) {
            return aToken.toLowerCase();
        }
        StringBuilder sb = new StringBuilder();
        for (char c : aToken.toCharArray()) {

            if(Character.isUpperCase(c) && canInsertUnderspace(sb)) {
                sb.append("_");
            }
            sb.append(c);

        }
        return sb.toString().toLowerCase();
    }

    private boolean canInsertUnderspace(StringBuilder sb) {
        if(sb.length() == 0) {
            return false;
        }

        return true;
    }

    private boolean shouldBeNormalized(String aToken) {
        for (char c : aToken.toCharArray()) {
            if(Character.isLowerCase(c)) {
                return true;
            }
        }
        return false;
    }

    private static class EnumFieldName {
        private final Class enumClass;
        private final String fieldName;

        public EnumFieldName(Class enumClass, String fieldName) {
            this.enumClass = enumClass;
            this.fieldName = fieldName;
        }
    }

}
