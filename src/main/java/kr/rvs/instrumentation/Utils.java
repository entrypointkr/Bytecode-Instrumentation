package kr.rvs.instrumentation;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Junhyeong Lim on 2017-02-02.
 */
public class Utils {
    public static String getClassName(Class cls) {
        return cls.getName().replace(".", "/") + ".class";
    }

    public static InputStream getStreamAsClass(Class cls) {
        return ClassLoader.getSystemResourceAsStream(getClassName(cls));
    }

    public static byte[] getBytesAsStream(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int read;
        byte[] data = new byte[65536];
        while ((read = in.read(data, 0, data.length)) != -1) {
            out.write(data, 0, read);
        }
        return out.toByteArray();
    }

    public static byte[] getBytesAsClass(Class cls) throws IOException {
        return getBytesAsStream(getStreamAsClass(cls));
    }
}
