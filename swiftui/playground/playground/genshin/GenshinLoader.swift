//
//  GenshinLoader.swift
//  playground
//
//  Created by Suraj-GP on 20/12/20.
//

import SwiftUI

let icons = ["anemo", "cryo", "dendro", "electro", "geo", "hydro", "pyro"].map {
        "\($0)_200"
    }

struct GenshinLoader: View {
    @State var sliderValue: Double = 0
    
    var body: some View {
        VStack {
            Spacer()
            ZStack(alignment: Alignment(horizontal: .leading, vertical: .center)
            ) {
                HStack(spacing: 0) {
                    ForEach(icons, id: \.self) { name in
                        Circle()
                            .fill(Color.gray)
                            .frame(width: 50, height: 50, alignment: .center)
                    }
                }
                Rectangle()
                    .fill(Color.red)
                    .frame(width: 70, height: 50)
            }
            
            Slider(value: $sliderValue, in: 0...1, step:0.001)
            Spacer()
        }
        .padding()
    }
}

struct GenshinLoader_Previews: PreviewProvider {
    static var previews: some View {
        GenshinLoader()
    }
}
