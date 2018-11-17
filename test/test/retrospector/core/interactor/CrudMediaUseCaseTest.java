
package test.retrospector.core.interactor;

import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import retrospector.core.boundry.CrudPresenter;
import retrospector.core.boundry.Interactor;
import retrospector.core.boundry.Request;
import retrospector.core.datagateway.DataGateway;
import retrospector.core.entity.Media;
import retrospector.core.interactor.CrudMediaRequest;
import retrospector.core.interactor.CrudMediaUseCase;
import retrospector.core.interactor.CrudRequest.Crud;
import retrospector.core.request.model.EntityConverter;
import retrospector.core.request.model.RequestableMedia;
import test.retrospector.core.utility.TestEntity;

@RunWith(MockitoJUnitRunner.class)
public class CrudMediaUseCaseTest {
    
    @Mock
    private DataGateway dataGateway;
    @Mock
    private CrudPresenter<RequestableMedia> presenter;
    private Interactor useCase;
    
    private Media media;
    private RequestableMedia requestableMedia;
    
    @Before
    public void setUp() {
        useCase = new CrudMediaUseCase(dataGateway, presenter);
        media = TestEntity.getMedia();
        requestableMedia = EntityConverter.convert(media);
        
        when(dataGateway.addMedia(media)).thenReturn(media);
        when(dataGateway.getMedia(media.getId())).thenReturn(media);
        when(dataGateway.updateMedia(media)).thenReturn(media);
    }
    
    @Test
    public void addMedia_CallsDataGateway() {
        Request request = new CrudMediaRequest(CrudMediaRequest.Crud.Create, requestableMedia);
        
        useCase.execute(request);
        
        verify(dataGateway, times(1)).addMedia(media);
    }
        
    @Test
    public void addMedia_CallsPresenter() {
        Request request = new CrudMediaRequest(Crud.Create, requestableMedia);
        
        useCase.execute(request);
        
        verify(presenter, times(1)).added(requestableMedia);
    }
    
    @Test
    public void readMedia_CallsDataGateway() {
        int mediaId = media.getId();
        Request request = new CrudMediaRequest(Crud.Read, mediaId);
        
        useCase.execute(request);
        
        verify(dataGateway, times(1)).getMedia(mediaId);
    }
    
    @Test
    public void readMedia_CallsPresenter() {
        int mediaId = media.getId();
        Request request = new CrudMediaRequest(Crud.Read, mediaId);
        
        useCase.execute(request);
        
        verify(presenter, times(1)).retrieved(requestableMedia);
    }
    
    @Test
    public void updateMedia_CallsDataGateway() {
        Request request = new CrudMediaRequest(Crud.Update, requestableMedia);
        
        useCase.execute(request);
        
        verify(dataGateway, times(1)).updateMedia(media);
    }
    
    @Test
    public void updateMedia_CallsPresenter() {
        Request request = new CrudMediaRequest(Crud.Update, requestableMedia);
        
        useCase.execute(request);
        
        verify(presenter, times(1)).updated(requestableMedia);
    }
    
    @Test
    public void deleteMedia_CallsDataGateway() {
        int mediaId = media.getId();
        Request request = new CrudMediaRequest(Crud.Delete, mediaId);
        
        useCase.execute(request);
        
        verify(dataGateway, times(1)).deleteMedia(mediaId);
    }
    
    @Test
    public void deleteMedia_CallsPresenter() {
        int mediaId = media.getId();
        Request request = new CrudMediaRequest(Crud.Delete, mediaId);
        
        useCase.execute(request);
        
        verify(presenter, times(1)).deleted(mediaId);
    }
    
    @Test
    public void readAllMedia_CallsDataGateway() {
        Request request = new CrudMediaRequest(Crud.ReadAll);
        
        useCase.execute(request);
        
        verify(dataGateway, times(1)).getMedia();
    }
    
    @Test
    public void readAllMedia_CallsPresenter() {
        ArgumentCaptor<List<RequestableMedia>> captor = ArgumentCaptor.forClass(List.class);
        List<Media> list = Arrays.asList(media);
        when(dataGateway.getMedia()).thenReturn(list);
        Request request = new CrudMediaRequest(Crud.ReadAll);
        
        useCase.execute(request);
        
        verify(presenter, times(1)).retrievedAll(captor.capture());
        assertEquals(list.size(), captor.getValue().size());
        assertEquals(requestableMedia, captor.getValue().get(0));
    }
}
