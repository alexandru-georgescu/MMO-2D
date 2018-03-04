package interfete;

/**
 * CONSANTE PENTRU A PUTEA MODIFICA CAT MAI USOR DMG-UL SPELLURILOR
 * CAT SI ALTE VALORI FARA A PARCURGE TOT CODUL CAP COADA
 * @author Alexandru Georgescu
 */
public interface MAGIC {
    int MINXP = 200;
    int MINXPLVL = 250;
    int XPCOUNT = 50;
    int MINXPCOUNT = 40;

    //PYRO
    float FIREBLEAST = 350F;
    float LVLUPFIREBLEAST = 50F;
    float IGNITEBONUS = 50F;
    float IGNITE = 150F;
    float LVLUPIGN = 20F;
    float LVLUPIGNBONUS = 30F;

    //KNIGHT
    float EXECUTE = 200F;
    float EXECUTEWHEN = 0.2F;
    float LVLUPEXECUTE = 30F;
    float PROCENTEXECUTEW = 0.4F;
    float LVLUPEXECUTEW = 0.1F;
    float SLAM = 100F;
    float LVLUPSLAM = 40F;

    //WIZARD
    int PROCENT = 100;
    float DRAIN = 20F;
    float LVLUPDRAIN = 0.5F;
    float HPPROC = 0.3F;
    float DEFLECT = 0.35F;
    float LVLUPDEFLECT = 0.2F;
    float DEFLECTMAX = 0.7F;

    //ROGUE
    int MOD3 = 3;
    int WITHWOODS = 6;
    int WITHOUTWOODS = 3;
    float BACKSTAB = 200F;
    float LVLUPBACKSTAB = 20F;
    float PARALYSIS = 40F;
    float LVLUPPARALYSIS = 10F;
}
