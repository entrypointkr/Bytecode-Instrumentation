package kr.rvs.instrumentation;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import static org.objectweb.asm.Opcodes.ASM5;

/**
 * Created by Junhyeong Lim on 2017-02-02.
 */
public class TargetTransformer implements ClassFileTransformer {
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        byte[] ret = classfileBuffer;
        if (className.equals("kr/rvs/instrumentation/Target")) {
            try {
                ClassReader reader = new ClassReader("kr.rvs.instrumentation.Target");
                ClassWriter writer = new ClassWriter(0);
                ClassVisitor visitor = new TargetClassVisitor(ASM5, writer);

                reader.accept(visitor, 0);
                ret = writer.toByteArray();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return ret;
    }
}
