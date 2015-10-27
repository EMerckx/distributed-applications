using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace WcfServiceLibrary1
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "IService1" in both code and config file together.
    [ServiceContract]
    public interface IService1
    {
        //Alle badmintonclubs opvragen. Voor elke club wordt de naam, de unieke identificatie en de tornooien meegegeven. 
        //De tornooi-informatie bestaat uit naam en id.
        [OperationContract]
        List<SportClubDTO> GeefClubs();

        //De leden van één badmintonclub opvragen. Voor elk lid wordt de naam en de unieke identificatie meegegeven. 
        //De club wordt gekenmerkt door zijn unieke identificatie.
        [OperationContract]
        List<LidDTO> GeefLeden(int clubId);
    }

    [DataContract]
    public class SportClubDTO
    {
        private string naam;
        private List<TornooiDTO> tornooien;
        private int id;

        [DataMember]
        public string Naam
        {
            get { return naam; }
            set { naam = value; }
        }

        [DataMember]
        public int Id
        {
            get { return id; }
            set { id = value; }
        }

        [DataMember]
        public List<TornooiDTO> Tornooien
        {
            get { return tornooien; }
            set { tornooien = value; }
        }
    }

    [DataContract]
    public class TornooiDTO
    {
        private string naam;
        private int id;

        [DataMember]
        public string Naam
        {
            get { return naam; }
            set { naam = value; }
        }

        [DataMember]
        public int Id
        {
            get { return id; }
            set { id = value; }
        }

    }

    [DataContract]
    public class LidDTO
    {
        private string naam;
        private int id;

        [DataMember]
        public string Naam
        {
            get { return naam; }
            set { naam = value; }
        }

        [DataMember]
        public int Id
        {
            get { return id; }
            set { id = value; }
        }
    }
}
