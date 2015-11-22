using System.Collections.Generic;
using System.Runtime.Serialization;
using System.ServiceModel;

namespace BadmintonServiceLibrary
{
    [ServiceContract]
    public interface IService1
    {
        [OperationContract]
        List<BadmintonClub> getBadmintonClubs();

        [OperationContract]
        List<BadmintonClubMember> getBadmintonClubMembers(int badmintonClubId);
    }

    [DataContract]
    public class BadmintonClub
    {
        private string name;
        private int id;
        private List<BadmintonTournament> tournaments;


        [DataMember]
        public string Name
        {
            get { return name; }
            set { name = value; }
        }

        [DataMember]
        public int Id
        {
            get { return id; }
            set { id = value; }
        }

        [DataMember]
        public List<BadmintonTournament> Tournaments
        {
            get { return tournaments; }
            set { tournaments = value; }
        }
    }

    [DataContract]
    public class BadmintonTournament
    {
        private string name;
        private int id;

        [DataMember]
        public string Name
        {
            get { return name; }
            set { name = value; }
        }

        [DataMember]
        public int Id
        {
            get { return id; }
            set { id = value; }
        }
    }

    [DataContract]
    public class BadmintonClubMember
    {
        private string name;
        private int id;

        [DataMember]
        public string Name
        {
            get { return name; }
            set { name = value; }
        }

        [DataMember]
        public int Id
        {
            get { return id; }
            set { id = value; }
        }
    }
}
