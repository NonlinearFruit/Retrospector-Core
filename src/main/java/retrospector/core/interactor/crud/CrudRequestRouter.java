package retrospector.core.interactor.crud;

import retrospector.core.boundry.Request;
import retrospector.core.boundry.RequestRouter;

public class CrudRequestRouter implements RequestRouter{
    private CrudMediaUseCase mediaUse;
    private CrudReviewUseCase reviewUse;
    private CrudFactoidUseCase factoidUse;
    private CrudRequest request;

    public CrudRequestRouter(CrudMediaUseCase mediaUse, CrudReviewUseCase reviewUse, CrudFactoidUseCase factoidUse) {
        this.mediaUse = mediaUse;
        this.reviewUse = reviewUse;
        this.factoidUse = factoidUse;
    }
    
    @Override
    public void disseminate(Request request) {
        this.request = (CrudRequest) request;
        
        if (request instanceof CrudMediaRequest)
            mediaUse.execute(request);
        else if (request instanceof CrudReviewRequest)
            reviewUse.execute(request);
        else if (request instanceof CrudFactoidRequest)
            factoidUse.execute(request);
    }
    
}
