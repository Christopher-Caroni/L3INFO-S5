import Parser

type Nom = String

data Expression = Lam Nom Expression
                | App Expression Expression
                | Var Nom
                | Lit Litteral
                deriving (Show,Eq)

data Litteral = Entier Integer
              | Bool   Bool
              deriving (Show,Eq)


espacesP :: Parser ()
espacesP = do many (car ' ')
              pure (())






--main :: IO ()
--main = _
