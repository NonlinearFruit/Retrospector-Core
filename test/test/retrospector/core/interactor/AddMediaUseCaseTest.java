
package test.retrospector.core.interactor;

import retrospector.core.boundry.Request;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import retrospector.core.boundry.Interactor;
import retrospector.core.boundry.Presenter;
import retrospector.core.datagateway.DataGateway;
import retrospector.core.entity.Media;
import retrospector.core.interactor.AddMediaRequest;
import retrospector.core.interactor.AddMediaUseCase;
import retrospector.core.request.model.EntityConverter;
import retrospector.core.request.model.RequestableMedia;
import test.retrospector.core.utility.TestEntity;


@RunWith(MockitoJUnitRunner.class)
public class AddMediaUseCaseTest {

    @Mock
    private DataGateway dataGateway;
    @Mock
    private Presenter presenter;
    private Interactor useCase;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        useCase = new AddMediaUseCase(dataGateway, presenter);
        when(dataGateway.addMedia(any())).thenAnswer(i -> i.getArguments()[0]);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void callsDataGateway() {
        Media media = TestEntity.getMedia();
        Request request = new AddMediaRequest(EntityConverter.convert(media));
        
        useCase.execute(request);
        
        verify(dataGateway, times(1)).addMedia(media);
    }
    
    @Test
    public void callsPresenter() {
        RequestableMedia media = TestEntity.getRequestableMedia();
        Request request = new AddMediaRequest(media);
        
        useCase.execute(request);
        
        verify(presenter, times(1)).mediaAdded(media);
    }
    
}
