package group2.cms.domain;

public enum Authority {
    Default(0),
    Listener(1),
    Author(2),
    PCMember(3),
    Chair(4),
    CoChair(4);

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
