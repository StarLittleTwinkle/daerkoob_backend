import { useRecoilState } from "recoil";
import { currentUserState } from "Store";
const useCurrentUser = () => {
  const [currentUser, setCurrentUser] = useRecoilState(currentUserState);

  return { currentUser, setCurrentUser };
};

export default useCurrentUser;
