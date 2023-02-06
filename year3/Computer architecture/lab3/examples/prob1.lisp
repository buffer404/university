( defvar r 0 )
( defvar i 0 )
( defvar max_i 999 )

( dotimes ( i max_i )

( cond
( ( = ( mod i 3 ) 0 )  ( setq r ( + r i ) ) )
( ( = ( mod i 5 ) 0 )  ( setq r ( + r i ) ) ) )

)

( print r )