package kr.rvs.instrumentation;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * Created by Junhyeong Lim on 2017-02-02.
 */
public class TransformClassVisitor extends ClassVisitor {
    public TransformClassVisitor(int i, ClassVisitor classVisitor) {
        super(i, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int i, String s, String s1, String s2, String[] strings) {
        MethodVisitor visitor = super.visitMethod(i, s, s1, s2, strings);
        if (!s.equals("<init>")) {
            return visitor;
        }
        return new TransformMethodVisitor(api, visitor);
    }
}
