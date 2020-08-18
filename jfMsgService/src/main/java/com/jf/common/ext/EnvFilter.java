package com.jf.common.ext;

import com.jf.common.enumerate.RuntimeEnv;
import com.jf.common.ext.util.AnnotationHelper;
import com.jf.common.ext.util.EnumUtil;
import com.jf.common.utils.Config;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;


/**
 * @author luoyb
 * Created on 2019/11/18
 */
public class EnvFilter implements TypeFilter {

    private RuntimeEnv env;
    private boolean hasSet;

    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        RegCondition regCondition = AnnotationHelper.findClassOrSuperAnnotation(classMetadata, RegCondition.class);
        RuntimeEnv level = RuntimeEnv.TEST; //默认 TEST level
        if (regCondition != null) {
            level = regCondition.level();
        }
        getEnv();
        switch (level) {
            case PROD:
                return RuntimeEnv.PROD == env;
            case TEST:
                return RuntimeEnv.PROD == env || RuntimeEnv.TEST == env;
            case LOCAL:
                return RuntimeEnv.PROD == env || RuntimeEnv.TEST == env || RuntimeEnv.LOCAL == env;
            default:
                return false;
        }
    }

    private void getEnv() {
        if (!hasSet) {
            String envStr = Config.getProperty("site.environment");
            if (envStr != null) {
                env = EnumUtil.toEnum(envStr.toUpperCase(), RuntimeEnv.class);
            }
            hasSet = true;
        }
    }
}
