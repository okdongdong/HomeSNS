package com.ssafy.homesns.dto;
import java.util.List;
public class MainFeedResultDto {
    private int result;
    private MainFeedDto mainFeedDto;
    private List<MainFeedDto> mainFeedDtoList;
    private List<LocationDto> locations;
    private List<GroupMemberDto> members;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public MainFeedDto getFeedDto() {
        return mainFeedDto;
    }

    public void setFeedDto(MainFeedDto mainFeedDto) {
        this.mainFeedDto = mainFeedDto;
    }

    public List<MainFeedDto> getFeedList() {
        return mainFeedDtoList;
    }

    public void setFeedList(List<MainFeedDto> mainFeedDtoList) {
        this.mainFeedDtoList = mainFeedDtoList;
    }

    public List<LocationDto> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationDto> locations) {
        this.locations = locations;
    }

    public List<GroupMemberDto> getMembers() {
        return members;
    }

    public void setMembers(List<GroupMemberDto> members) {
        this.members = members;
    }


}
