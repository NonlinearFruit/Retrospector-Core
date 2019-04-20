
package retrospector.core.request.model;

import retrospector.core.request.model.RequestableFactoid;
import retrospector.core.request.model.EntityConverter;
import retrospector.core.request.model.RequestableReview;
import retrospector.core.request.model.RequestableMedia;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import retrospector.core.entity.Factoid;
import retrospector.core.entity.Media;
import retrospector.core.entity.Review;
import retrospector.core.utility.TestEntity;

public class EntityConverterTest {
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testConvertToMediaAndBack() {
        RequestableMedia expected = TestEntity.getRequestableMedia();
        
        Media middleman = EntityConverter.convert(expected);
        RequestableMedia result = EntityConverter.convert(middleman);
        
        assertEquals(expected, result);
    }
    
    @Test
    public void testConvertToReviewAndBack() {
        RequestableReview expected = TestEntity.getRequestableReview();
        
        Review middleman = EntityConverter.convert(expected);
        RequestableReview result = EntityConverter.convert(middleman);
        
        assertEquals(expected, result);
    }
        
    @Test
    public void testConvertToFactoidAndBack() {
        RequestableFactoid expected = TestEntity.getRequestableFactoid();
        
        Factoid middleman = EntityConverter.convert(expected);
        RequestableFactoid result = EntityConverter.convert(middleman);
        
        assertEquals(expected, result);
    }
            
    @Test
    public void testConvertToTypeAndBack() {
        for (RequestableMedia.RequestableType expected : RequestableMedia.RequestableType.values()) {
            Media.Type middleman = EntityConverter.convert(expected);
            RequestableMedia.RequestableType result = EntityConverter.convert(middleman);

            assertEquals(expected, result);
        }
    }
}
