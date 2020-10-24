using Retrospector.Core.Boundary;
using Retrospector.Core.Crud.Interfaces;
using Retrospector.Core.Crud.Models;

namespace Retrospector.Core.Tests.TestDoubles.Crud
{
    public class CrudUseCase_TestDouble<T> : IUseCase<CrudRequest<T>> where T : IModel
    {
        public int CountOf_Execute_Calls { get; set; }
        public CrudRequest<T> LastRequestPassedTo_Execute { get; set; }
        public void Execute(CrudRequest<T> request)
        {
            CountOf_Execute_Calls++;
            LastRequestPassedTo_Execute = request;
        }
    }
}