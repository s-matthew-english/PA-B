/**
 * Introduction to Neural Networks with Java, 2nd Edition
 * Copyright 2008 by Heaton Research, Inc. 
 * http://www.heatonresearch.com/books/java-neural-2/
 * 
 * ISBN13: 978-1-60439-008-7  	 
 * ISBN:   1-60439-008-5
 *   
 * This class is released under the:
 * GNU Lesser General Public License (LGPL)
 * http://www.gnu.org/copyleft/lesser.html
 */
package pa.b;

/**
 * Chapter 5: The Feedforward Backpropagation Neural Network
 * 
 * XOR: Learn the XOR pattern with a feedforward neural network that
 * uses backpropagation.
 * 
 * @author Jeff Heaton
 * @version 2.1
 */
public class Main 
{

//	public static double XOR_INPUT[][] = { 
//											{ 0.0, 0.0 }, 
//											{ 1.0, 0.0 },
//											{ 0.0, 1.0 }, 
//											{ 1.0, 1.0 } 
//										 };
	
	public static double XOR_INPUT[][] = { 	
											{ 0, 0, 0, 0, 0 },
											{ 0, 0, 0, 0, 1 },
											{ 0, 0, 0, 1, 0 },
											{ 0, 0, 0, 1, 1 },
											{ 0, 0, 1, 0, 0 },
											{ 0, 0, 1, 0, 1 },
											{ 0, 0, 1, 1, 0 },
											{ 0, 0, 1, 1, 1 },
											{ 0, 1, 0, 0, 0 },
											{ 0, 1, 0, 0, 1 },
											{ 0, 1, 0, 1, 0 },
											{ 0, 1, 0, 1, 1 },
											{ 0, 1, 1, 0, 0 },
											{ 0, 1, 1, 0, 1 },
											{ 0, 1, 1, 1, 0 },
											{ 0, 1, 1, 1, 1 },
											{ 1, 0, 0, 0, 0 },
											{ 1, 0, 0, 0, 1 },
											{ 1, 0, 0, 1, 0 },
											{ 1, 0, 0, 1, 1 },
											{ 1, 0, 1, 0, 0 },
											{ 1, 0, 1, 0, 1 },
											{ 1, 0, 1, 1, 0 },
											{ 1, 0, 1, 1, 1 },
											{ 1, 1, 0, 0, 0 },
											{ 1, 1, 0, 0, 1 },
											{ 1, 1, 0, 1, 0 },
											{ 1, 1, 0, 1, 1 },
											{ 1, 1, 1, 0, 0 },	
											{ 1, 1, 1, 0, 1 },
											{ 1, 1, 1, 1, 0 },
											{ 1, 1, 1, 1, 1 }
										};
	
	public static double XOR_IDEAL[][] = { 
										    {0, 0},
											{0, 0},
											{0, 0},
											{0, 0},
											{0, 0},
											{0, 0},
											{0, 0},
											{1, 0},
											{0, 0},
											{0, 1},
											{0, 0},
											{1, 1},
											{0, 0},
											{1, 1},
											{1, 0},
											{1, 1},
											{0, 0},
											{0, 0},
											{0, 0},
											{1, 0},
											{0, 0},
											{1, 0},
											{1, 0},
											{1, 0},
											{0, 0},
											{1, 1},
											{1, 0},
											{1, 1},
											{1, 0},
											{1, 1},
											{1, 0},
											{1, 1},
										};
	
	
//
//	public static double XOR_IDEAL[][] = { 
//											{ 0.0 }, 
//											{ 1.0 }, 
//											{ 1.0 }, 
//											{ 0.0 } 
//										 };

	public static void main(final String args[]) 
	{
		/*****************************************************
		 *            DEFINE OUR LAYERS HERE                 *
		 *****************************************************/
		FeedforwardLayer layer_one = new FeedforwardLayer(5);
		FeedforwardLayer layer_two = new FeedforwardLayer(5);
		FeedforwardLayer layer_three = new FeedforwardLayer(2);
		
		/*****************************************************
		 *            ADD THEM TO THE NETWORK                *
		 *****************************************************/
		final FeedforwardNetwork network = new FeedforwardNetwork();
		network.addLayer( layer_one );
		network.addLayer( layer_two );
		network.addLayer( layer_three );
		network.reset();

		// train the neural network
		final Train train = new Backpropagation( 
				 									network, 
				 									XOR_INPUT, 
				 									XOR_IDEAL,
				 									0.7, 
				 									0.9
											   );

		int epoch = 1;

		do 
		{
			train.iteration();
			
			System.out.println("Epoch #" + epoch + " Error:" + train.getError());
			
			epoch++;
		} 
		while ((epoch < 5000) && (train.getError() > 0.001));

		
		// test the neural network
		System.out.println("Neural Network Results:");
		
		for (int i = 0; i < XOR_IDEAL.length; i++) 
		{
			final double actual[] = network.computeOutputs(XOR_INPUT[i]);
			
		System.out.println( XOR_INPUT[i][0] + ", " + XOR_INPUT[i][1] + ", " + XOR_INPUT[i][2] + ", " + XOR_INPUT[i][3] + ", " + XOR_INPUT[i][4] ); 
		System.out.println(	"Actual=" + actual[0] + ", " + actual[1] ); 
		System.out.println(	"Ideal=" + XOR_IDEAL[i][0] + ", " + XOR_IDEAL[i][1] );
		System.out.println();
		System.out.println();
		}
	}
}