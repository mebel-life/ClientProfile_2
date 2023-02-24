package org.client.service;

import org.client.dto.AvatarDto;
import org.client.entity.Individual;
import org.client.entity.RFPassport;

public interface IndividualService {

    public Individual findByIcp(String icp);

    public void saveIndividual(Individual individual);

    public void saveRFPassport(RFPassport rfPassport, String icp);

    public void saveAvatarDto(org.client.entity.AvatarDto avatarDto, String uuid);
}
