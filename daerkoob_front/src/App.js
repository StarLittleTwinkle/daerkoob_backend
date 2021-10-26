import Router from "Router";
import "styles/global.scss";
import { RecoilRoot } from "recoil";
function App() {
  return (
    <RecoilRoot>
      <Router />
    </RecoilRoot>
  );
}

export default App;
