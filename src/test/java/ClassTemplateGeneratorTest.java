import com.robohorse.robopojogenerator.generator.consts.ClassTemplate;
import com.robohorse.robopojogenerator.generator.utils.ClassGenerateHelper;
import com.robohorse.robopojogenerator.generator.utils.ClassTemplateGenerator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by vadim on 05.10.16.
 */
public class ClassTemplateGeneratorTest {
    @InjectMocks
    ClassTemplateGenerator classTemplateGenerator;

    @Mock
    ClassGenerateHelper classGenerateHelper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateSetter() throws Exception {
        final String field = "item";
        final String type = "String";
        final String fieldUpper = "Item";
        when(classGenerateHelper.upperCaseFirst(field))
                .thenReturn(fieldUpper);

        final String target = ClassTemplate.TAB + "public void set" + fieldUpper + "(" + type + " " + field + "){"
                + ClassTemplate.NEW_LINE +
                ClassTemplate.TAB + ClassTemplate.TAB + "this." + field + " = " + field + ";"
                + ClassTemplate.NEW_LINE
                + ClassTemplate.TAB + "}"
                + ClassTemplate.NEW_LINE;
        assertEquals(target, classTemplateGenerator.createSetter(field, type));
    }

    @Test
    public void testCreateGetter() throws Exception {
        final String field = "item";
        final String type = "String";
        final String fieldUpper = "Item";
        when(classGenerateHelper.upperCaseFirst(field))
                .thenReturn(fieldUpper);

        final String target = ClassTemplate.TAB + "public " + type + " get" + fieldUpper + "(){"
                + ClassTemplate.NEW_LINE +
                ClassTemplate.TAB + ClassTemplate.TAB + "return " + field + ";"
                + ClassTemplate.NEW_LINE
                + ClassTemplate.TAB + "}"
                + ClassTemplate.NEW_LINE;
        assertEquals(target, classTemplateGenerator.createGetter(field, type));
    }

    @Test
    public void testCreateGetterBoolean() throws Exception {
        final String field = "item";
        final String type = "boolean";
        final String fieldUpper = "Item";
        when(classGenerateHelper.upperCaseFirst(field))
                .thenReturn(fieldUpper);

        final String target = ClassTemplate.TAB + "public boolean is" + fieldUpper + "(){"
                + ClassTemplate.NEW_LINE +
                ClassTemplate.TAB + ClassTemplate.TAB + "return " + field + ";"
                + ClassTemplate.NEW_LINE
                + ClassTemplate.TAB + "}"
                + ClassTemplate.NEW_LINE;
        assertEquals(target, classTemplateGenerator.createGetter(field, type));
    }

    @Test
    public void testCreateField() throws Exception {
        final String field = "item";
        final String type = "boolean";
        final String target = ClassTemplate.TAB + "private " + type + " " + field + ";"
                + ClassTemplate.NEW_LINE;
        assertEquals(target, classTemplateGenerator.createFiled(type, field, null));
    }

    @Test
    public void testCreateFieldGenerated() throws Exception {
        final String field = "item";
        final String type = "boolean";
        final String annotation = "@JsonField";
        final String target = ClassTemplate.TAB + annotation + ClassTemplate.NEW_LINE +
                ClassTemplate.TAB + "private " + type + " " + field + ";"
                + ClassTemplate.NEW_LINE;
        assertEquals(target, classTemplateGenerator.createFiled(type, field, annotation));
    }
}
