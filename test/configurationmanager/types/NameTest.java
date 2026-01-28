package configurationmanager.types;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NameTest {

    @Test
    void shouldRejectNull(){
        assertThrows(NullPointerException.class, ()->new Name(null));

    }

    @Test
    void shouldReturnSameValue() {
        Name name = new Name("Pino");
        assertEquals("Pino", name.value());
    }


}
