using System.Collections.Generic;
using Retrospector.Core.Crud.Interfaces;

namespace Retrospector.Core.Tests.TestDoubles.Crud
{
    public class CrudPresenter_TestDouble<T> : ICrudPresenter<T> where T : IModel
    {
        public int CountOf_Added_Calls{ get; set; }
        public T LastModelPassedTo_Added{ get; set; }
        public void Added(T entity)
        {
            CountOf_Added_Calls++;
            LastModelPassedTo_Added = entity;
        }

        public void Retrieved(T entity)
        {
            throw new System.NotImplementedException();
        }

        public void Updated(T entity)
        {
            throw new System.NotImplementedException();
        }

        public void Deleted(int id)
        {
            throw new System.NotImplementedException();
        }

        public void RetrievedAll(IEnumerable<T> entities)
        {
            throw new System.NotImplementedException();
        }

        public void RetrievedAllByMediaId(IEnumerable<T> entities)
        {
            throw new System.NotImplementedException();
        }
    }
}