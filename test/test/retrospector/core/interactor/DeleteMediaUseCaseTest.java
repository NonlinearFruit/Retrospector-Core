
package test.retrospector.core.interactor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;
import retrospector.core.boundry.Interactor;
import retrospector.core.boundry.Presenter;
import retrospector.core.boundry.Request;
import retrospector.core.datagateway.DataGateway;
import retrospector.core.interactor.DeleteMediaRequest;
import retrospector.core.interactor.DeleteMediaUseCase;
import test.retrospector.core.utility.TestEntity;

@RunWith(MockitoJUnitRunner.class)
public class DeleteMediaUseCaseTest {
    
    @Mock
    private DataGateway dataGateway;
    @Mock
    private Presenter presenter;
    private Interactor useCase;
    
    @Before
    public void setUp() {
        useCase = new DeleteMediaUseCase(dataGateway, presenter);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void callsDataGateway() {
        int mediaId = TestEntity.getMedia().getId();
        Request request = new DeleteMediaRequest(mediaId);
        
        useCase.execute(request);
        
        verify(dataGateway, times(1)).deleteMedia(mediaId);
    }
    
    @Test
    public void callsPresenter() {
        int mediaId = TestEntity.getMedia().getId();
        Request request = new DeleteMediaRequest(mediaId);
        
        useCase.execute(request);
        
        verify(presenter, times(1)).mediaDeleted(mediaId);
    }
}
