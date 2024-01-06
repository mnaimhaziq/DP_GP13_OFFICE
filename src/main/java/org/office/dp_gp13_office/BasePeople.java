package org.office.dp_gp13_office;

public class BasePeople extends Entity {
    private static final int PEOPLE_TYPE_COUNT = 5;
    public OfficeSpace office;
    public Space space;

    public BasePeople(OfficeSpace office, Space space, int pplRandom, int countTom, int countJohn, int countBen, int countAnne, int countSelina) {
        this.office = office;
        this.space = space;

        try {
            switch(pplRandom) {
                case 0:
                    this.name = "tom";
                    this.assetPath = App.class.getResource("images/people_tom.png").toExternalForm();
                    if(countTom == 2){
                        Badge tomBadge = TomBadge.getInstance(office);
                        if(tomBadge != null){
                            tomBadge.printBadgeWithDescription();
                        }
                        space.getBadgeList().get(0).setVisible(true);
                    }
                    break;
                case 1:
                    this.name = "john";
                    this.assetPath = App.class.getResource("images/people_john.png").toExternalForm();
                    if(countJohn == 2){
                        Badge johnBadge = JohnBadge.getInstance(office);
                        if(johnBadge != null){
                            johnBadge.printBadgeWithDescription();
                        }
                        space.getBadgeList().get(1).setVisible(true);
                    }
                    break;
                case 2:
                    this.name = "ben";
                    this.assetPath = App.class.getResource("images/people_ben.png").toExternalForm();
                    if(countBen == 2){
                        Badge benBadge = BenBadge.getInstance(office);
                        if(benBadge != null){
                            benBadge.printBadgeWithDescription();
                        }
                        space.getBadgeList().get(2).setVisible(true);
                    }
                    break;
                case 3:
                    this.name = "anne";
                    this.assetPath = App.class.getResource("images/people_anne.png").toExternalForm();
                    if(countAnne == 2){
                        Badge anneBadge = AnneBadge.getInstance(office);
                        if(anneBadge != null){
                            anneBadge.printBadgeWithDescription();
                        }
                        space.getBadgeList().get(3).setVisible(true);
                    }
                    break;
                case 4:
                    this.name = "selina";
                    this.assetPath = App.class.getResource("images/people_selina.png").toExternalForm();
                    if(countSelina == 2){
                        Badge selinaBadge = SelinaBadge.getInstance(office);
                        if(selinaBadge != null){
                            selinaBadge.printBadgeWithDescription();
                        }
                        space.getBadgeList().get(4).setVisible(true);
                    }
                    break;
                default:
                    throw new Exception();
            }
        }
        catch (Exception e) {
            System.out.println("An exception occurred when generating people");
        }
    }
}
