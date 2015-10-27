using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;
using BadmintonInterface;

namespace WcfServiceLibrary1
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "Service1" in both code and config file together.
    public class Service1 : IService1
    {
        public List<SportClubDTO> GeefClubs()
        {
            BadmintonDAODummy badmintonDAODummy = new BadmintonDAODummy();
            SportClub[] sportClubArray = badmintonDAODummy.SportClubs;

            List<SportClubDTO> sportClubDTOList = new List<SportClubDTO>();
            foreach(SportClub sportClub in sportClubArray){
                SportClubDTO sportClubDTO = new SportClubDTO();
                sportClubDTO.Id = sportClub.ID;
                sportClubDTO.Naam = sportClub.Naam;

                sportClubDTO.Tornooien = new List<TornooiDTO>();
                foreach(Tornooi tornooi in sportClub.Tornooien){
                    TornooiDTO tornooiDTO = new TornooiDTO();
                    tornooiDTO.Id = tornooi.ID;
                    tornooiDTO.Naam = tornooi.Naam;

                    sportClubDTO.Tornooien.Add(tornooiDTO);
                }

                sportClubDTOList.Add(sportClubDTO);
            }

            return sportClubDTOList;
        }

        public List<LidDTO> GeefLeden(int clubId)
        {
            BadmintonDAODummy badmintonDAODummy = new BadmintonDAODummy();
            Lid[] lidArray = badmintonDAODummy.GeefLeden(clubId);

            List<LidDTO> lidDtoList = new List<LidDTO>();
            foreach(Lid lid in lidArray){
                LidDTO lidDto = new LidDTO();
                lidDto.Id = lid.ID;
                lidDto.Naam = lid.Naam;

                lidDtoList.Add(lidDto);
            }

            return lidDtoList;
        }
    }
}
