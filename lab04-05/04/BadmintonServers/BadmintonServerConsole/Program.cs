using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel;
using System.Text;
using System.Threading.Tasks;

namespace BadmintonServerConsole
{
    class Program
    {
        static void Main(string[] args)
        {
            using (ServiceHost serviceHost = new ServiceHost(typeof(BadmintonServiceLibrary.Service1)))
            {
                serviceHost.Open();

                Console.WriteLine("The server is ready ");

                Console.ReadLine();
            }
        }
    }
}
