package me.stay1444;

import com.google.gson.GsonBuilder;
import org.objectweb.asm.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        if (args.length < 1) {
            System.err.println("Please specify the path to the class file");
            return;
        }

        var inputStream = new FileInputStream(args[0]);

        var info = new ClassInfo();

        var fields = new ArrayList<FieldInfo>();
        var methods = new ArrayList<MethodInfo>();

        var reader = new ClassReader(inputStream);
        reader.accept(new ClassVisitor(Opcodes.ASM9) {
            ClassType type = ClassType.Class;

            @Override
            public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                if ((access & Opcodes.ACC_ENUM) != 0) {
                    type = ClassType.Enum;
                }

                if ((access & Opcodes.ACC_ANNOTATION) != 0) {
                    type = ClassType.Annotation;
                }

                if ((access & Opcodes.ACC_INTERFACE) != 0) {
                    type = ClassType.Interface;
                }

                info.name = name.replace("/", ".");
                info.parentClass = (superName != null ? superName.replace("/", ".") : null);
                info.implementedInterfaces = Arrays.stream(interfaces).map(x -> x.replace("/", ".")).collect(Collectors.toList()).toArray(new String[0]);
                info.modifiers = me.stay1444.Modifier.fromInt(access);
                info.type = type;
            }

            @Override
            public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {

                var field = new FieldInfo();
                field.name = name;
                field.type = descriptor;
                field.isEnumVariant = type == ClassType.Enum && (access & Opcodes.ACC_ENUM) != 0;
                field.modifiers = me.stay1444.Modifier.fromInt(access);
                fields.add(field);

                return super.visitField(access, name, descriptor, signature, value);
            }

            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                var method = new MethodInfo();
                method.name = name.replace("/", ".");
                method.modifiers = me.stay1444.Modifier.fromInt(access);
                method.returnType = Type.getReturnType(descriptor).getClassName();

                var params = new ArrayList<String>();

                var argTypes = Type.getArgumentTypes(descriptor);
                for (Type arg : argTypes) {
                    params.add(arg.getClassName().replace("/", "."));
                }

                method.parameters = params.toArray(new String[0]);

                methods.add(method);

                return super.visitMethod(access, name, descriptor, signature, exceptions);
            }
        }, 0);

        info.methods = methods.toArray(new MethodInfo[0]);
        info.fields = fields.toArray(new FieldInfo[0]);

        var json = new GsonBuilder().setPrettyPrinting().create();

        System.out.println(json.toJson(info));
    }
}