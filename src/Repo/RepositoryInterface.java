package Repo;

import Model.PrgState;
import Utils.Interfaces.MyIList;

import java.util.List;

public interface RepositoryInterface {
    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> list);
    void logPrgStateExec(PrgState state);
}
