using System.Collections.Generic;
using Retrospector.Core.Crud.Interfaces;

namespace Retrospector.Core.Tests.TestDoubles.Crud
{
    public class CrudDataGateway_TestDouble<T> : ICrudDataGateway<T> where T : IModel
    {
        public int CountOf_Add_Calls{ get; set; }
        public T LastModelPassedTo_Add{ get; set; }
        public T ReturnFor_Add{ get; set; }
        public T Add(T model)
        {
            CountOf_Add_Calls++;
            LastModelPassedTo_Add = model;
            return ReturnFor_Add;
        }

        public T Get(int id)
        {
            throw new System.NotImplementedException();
        }

        public T Update(T entity)
        {
            throw new System.NotImplementedException();
        }

        public void Delete(int id)
        {
            throw new System.NotImplementedException();
        }

        public IEnumerable<T> GetAll()
        {
            throw new System.NotImplementedException();
        }

        public IEnumerable<T> GetByMediaId(int mediaId)
        {
            throw new System.NotImplementedException();
        }
    }
}