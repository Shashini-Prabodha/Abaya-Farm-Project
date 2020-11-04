package lk.abayafarm.pos.bo.custom;

import lk.abayafarm.pos.dto.FeedDTO;
import lk.abayafarm.pos.dto.FeedDetailsDTO;


public interface FeedServingBO {
    public boolean feedServe(FeedDTO feedDTO, FeedDetailsDTO feedDetailsDTO)throws Exception;
}
