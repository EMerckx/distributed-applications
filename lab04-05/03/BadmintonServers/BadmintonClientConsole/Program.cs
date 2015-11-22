using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using BadmintonClientConsole.BadmintonServiceReference;

namespace BadmintonClientConsole
{
    class Program
    {
        static void Main(string[] args)
        {
            // create a new web service client
            Service1Client service1Client = new Service1Client();

            // write the clubs to the screen
            BadmintonClub[] badmintonClubArray = service1Client.getBadmintonClubs();
            foreach (BadmintonClub badmintonClub in badmintonClubArray)
            {
                // show the id and name of the club
                Console.WriteLine("ID: " + badmintonClub.Id + "\t Name: " + badmintonClub.Name);

                // if there are tournaments, show them
                if (badmintonClub.Tournaments != null &&
                    badmintonClub.Tournaments.Length > 0)
                {
                    Console.WriteLine("Tournaments:");
                    foreach (BadmintonTournament badmintonTournament in badmintonClub.Tournaments)
                    {
                        Console.WriteLine("\t " + badmintonTournament.Name);
                    }
                }
                Console.WriteLine("");
            }

            // get the id of a club from the user
            Console.WriteLine("Enter an id of a club: ");
            string idString = Console.ReadLine();
            int id = int.Parse(idString);
            Console.WriteLine("Members: ");

            // get the members and write them to the screen
            BadmintonClubMember[] badmintonClubMemberArray = service1Client.getBadmintonClubMembers(id);
            foreach (BadmintonClubMember badmintonClubMember in badmintonClubMemberArray)
            {
                Console.WriteLine("\t " + badmintonClubMember.Name);
            }

            // close the web service client
            service1Client.Close();
        }
    }
}
