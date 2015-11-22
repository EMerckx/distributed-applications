using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;
using BadmintonInterface;

namespace BadmintonServiceLibrary
{
    public class Service1 : IService1
    {

        public List<BadmintonClub> getBadmintonClubs()
        {
            // get the sport clubs from the BadmintonDAODummy
            BadmintonDAODummy badmintonDaoDummy = new BadmintonDAODummy();
            SportClub[] sportClubArray;
            try
            {
                sportClubArray = badmintonDaoDummy.SportClubs;
            }
            catch (Exception ex)
            {
                return new List<BadmintonClub>();
            }

            // map the sport clubs on a list of BadmintonClub
            List<BadmintonClub> badmintonClubList = new List<BadmintonClub>();
            foreach (SportClub sportClub in sportClubArray)
            {
                // create a new BadmintonClub and map the id and name
                BadmintonClub badmintonClub = new BadmintonClub();
                badmintonClub.Id = sportClub.ID;
                badmintonClub.Name = sportClub.Naam;

                // create a new list of BadmintonTournament and map the tournaments
                List<BadmintonTournament> badmintonTournamentList
                    = new List<BadmintonTournament>();
                foreach (Tornooi tornooi in sportClub.Tornooien)
                {
                    // create a new BadmintonTournament and map the id and name
                    BadmintonTournament badmintonTournament = new BadmintonTournament();
                    badmintonTournament.Id = tornooi.ID;
                    badmintonTournament.Name = tornooi.Naam;

                    // add the BadmintonTournament to the list
                    badmintonTournamentList.Add(badmintonTournament);
                }
                badmintonClub.Tournaments = badmintonTournamentList;

                // add the BadmintonClub to the list
                badmintonClubList.Add(badmintonClub);
            }

            // return the List of BadmintonClub
            return badmintonClubList;
        }

        public List<BadmintonClubMember> getBadmintonClubMembers(int badmintonClubId)
        {
            // get the members from the BadmintonDAODummy
            BadmintonDAODummy badmintonDaoDummy = new BadmintonDAODummy();
            Lid[] lidArray;
            try
            {
                lidArray = badmintonDaoDummy.GeefLeden(badmintonClubId);
            }
            catch (Exception ex)
            {
                return new List<BadmintonClubMember>();
            }

            // create a new list of BadmintonClubMember
            List<BadmintonClubMember> badmintonClubMemberList
                = new List<BadmintonClubMember>();

            // map the array to the list of BadmintonClubMember
            foreach (Lid lid in lidArray)
            {
                // create a new BadmintonClubMember and map the id and name
                BadmintonClubMember badmintonClubMember = new BadmintonClubMember();
                badmintonClubMember.Id = lid.ID;
                badmintonClubMember.Name = lid.Naam;

                // add the BadmintonClubMember to the list
                badmintonClubMemberList.Add(badmintonClubMember);
            }

            // return the list of BadmintonClubMember
            return badmintonClubMemberList;
        }
    }
}
