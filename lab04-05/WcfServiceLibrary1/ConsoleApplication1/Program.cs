using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ConsoleApplication1.ServiceReference1;

namespace ConsoleApplication1
{
    class Program
    {
        static void Main(string[] args)
        {
            Service1Client client = new ServiceReference1.Service1Client();
            SportClubDTO[] sportClubDTOList = client.GeefClubs();

            foreach (SportClubDTO sportClubDto in sportClubDTOList)
            {
                Console.WriteLine("Sportclub: " + sportClubDto.Naam + " \t id:" + sportClubDto.Id);
                Console.WriteLine("\tTornooien: ");
                foreach (TornooiDTO tornooiDto in sportClubDto.Tornooien)
                {
                    Console.WriteLine("\t -> " + tornooiDto.Naam);
                }
            }

            int id = 3;
            Console.WriteLine("\nLeden van club met ID = " + id);

            LidDTO[] lidDtoList = client.GeefLeden(id);

            foreach (LidDTO lidDto in lidDtoList)
            {
                Console.WriteLine(" - Lid: " + lidDto.Naam);
            }
            

            Console.Read();
        }
    }
}
