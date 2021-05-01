package group2.cms.domain;

public enum Authority {
    Listener(0),
    Author(1),
    PCMember(2),
    Chair(3),
    CoChair(3);

    private final int authorityLevel;

    Authority(int authorityLevel) {
        this.authorityLevel = authorityLevel;
    }


    public int getAuthorityLevel() {
        return authorityLevel;
    }


    /**
     *
     * @param authority - authority object with the level selected e.g Authority.Author
     * @return - true if authority given is <= the authority from which the compare function is called
     */
    public boolean compareAuthority(Authority authority){
        return this.authorityLevel >= authority.getAuthorityLevel();
    }
}
