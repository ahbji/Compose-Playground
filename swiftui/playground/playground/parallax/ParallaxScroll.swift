//
//  ParallaxScroll.swift
//  playground
//
//  Created by Suraj-GP on 08/12/20.
//

import SwiftUI

let Items = [
    Item(title: "Velkhana", subTitle: "イヴェルカナ", image: "velkhana"),
    Item(title: "Fatalis", subTitle: "ミラボレアス", image: "fatalis"),
    Item(title: "Alatreon", subTitle: "アルバトリオン", image: "alatreon"),
    Item(title: "Nergigante", subTitle: "ネルギガンテ", image: "nergi"),
    Item(title: "Mizutsune", subTitle: "タミツネ", image: "mizutsune")
]

private struct ScrollOffsetPrefKey: PreferenceKey {
    static var defaultValue: CGPoint = .zero
    
    static func reduce(value: inout CGPoint, nextValue: () -> CGPoint) {
        value = nextValue()
    }
}



struct ParallaxScroll: View {
    
    var body: some View {
        GeometryReader { view in
            ScrollView(.horizontal, showsIndicators: false) {
                HStack {
                    ForEach(Items, id: \.self) { item in
                        CardView(scrollx: view.frame(in: .global).minX, item: item)
                            .padding()
                    }
                }
            }
        }
        
    }
}

struct CardView: View {
    var scrollx: CGFloat
    var item: Item
    
    var body: some View {
        ZStack {
            RoundedRectangle(cornerRadius: 20.0, style: .continuous)
                .fill(Color.white)
            
            VStack {
                Image(item.image)
                    .scaledToFill()
                    .frame(width: 350, height: 400, alignment: /*@START_MENU_TOKEN@*/.center/*@END_MENU_TOKEN@*/)
                    .offset(x: scrollx, y: 0.0)
                    .clipped()
                
                HStack {
                    Text(item.title)
                        .font(.title)
                        .fontWeight(.bold)
                        .padding(.leading, 16.0)
                    
                    Spacer()
                }
                
                
                HStack {
                    Text(item.subTitle)
                        .font(.subheadline)
                        .fontWeight(.medium)
                        .multilineTextAlignment(.leading)
                        .padding(.leading, 16.0)
                        .padding(.top, 8.0)
                    
                    Spacer()
                }
                
                Spacer()
                
            }
        }
        .frame(width: 350, height: 500, alignment: .leading)
        .shadow(radius: 10)
    }
}

struct ParallaxScroll_Previews: PreviewProvider {
    static var previews: some View {
        Group {
            ParallaxScroll()
                .previewDisplayName("ParallaxScroll")
            CardView(scrollx: 0.0, item: Items[0])
                .previewLayout(.fixed(width: 600, height: 600))
                .previewDisplayName("CardView")
        }
    }
}

struct Item: Hashable {
    var title: String
    var subTitle: String
    var image: String
}
